package vn.edu.nlu.fit.web.chat.repositoriy.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import vn.edu.nlu.fit.web.chat.dto.mapper.UserDtoMapper;
import vn.edu.nlu.fit.web.chat.dto.response.PageResponse;
import vn.edu.nlu.fit.web.chat.model.Address;
import vn.edu.nlu.fit.web.chat.model.User;
import vn.edu.nlu.fit.web.chat.repositoriy.UserSearchRepository;
import vn.edu.nlu.fit.web.chat.repositoriy.criteria.SearchCriteria;
import vn.edu.nlu.fit.web.chat.repositoriy.criteria.UserSearchQueryCriteriaConsumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static vn.edu.nlu.fit.web.chat.utils.AppConst.SEARCH_OPERATOR;
import static vn.edu.nlu.fit.web.chat.utils.AppConst.SORT_BY;

@RequiredArgsConstructor
@Repository
@Slf4j
public class UserSearchRepositoryImpl implements UserSearchRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final UserDtoMapper userDtoMapper;


    private static final String LIKE_FORMAT = "%%%s%%";

    @Override
    public PageResponse searchUsersWithPaginationAndSorting(int pageNo, int pageSize, String search, String sortBy) {

        StringBuilder selectSql = new StringBuilder("SELECT u FROM User u WHERE");
        if (StringUtils.hasLength(search)) {
            selectSql.append(" LOWER(u.firstName) LIKE LOWER(:search)");
            selectSql.append(" OR LOWER(u.lastName) LIKE LOWER(:search)");
            selectSql.append(" OR LOWER(u.email) LIKE LOWER(:search)");
            selectSql.append(" OR LOWER(u.phoneNumber) LIKE LOWER(:search)");
        }
        Query selectQuery = entityManager.createQuery(selectSql.toString());
        selectQuery.setFirstResult((pageNo - 1) * pageSize); // page index start from 0
        selectQuery.setMaxResults(pageSize);
        if (StringUtils.hasLength(search)) {
            selectQuery.setParameter("search", String.format("%%%s%%", search));
        }
        List mappedUsers = selectQuery.getResultList().stream().map(userDtoMapper).toList();

        StringBuilder countSql = new StringBuilder("SELECT COUNT(u) FROM User u WHERE");
        if (StringUtils.hasLength(search)) {
            countSql.append(" LOWER(u.firstName) LIKE LOWER(:search)");
            countSql.append(" OR LOWER(u.lastName) LIKE LOWER(:search)");
            countSql.append(" OR LOWER(u.email) LIKE LOWER(:search)");
        }
        Query countQuery = entityManager.createQuery(countSql.toString());
        Long totalRecord = (Long) countQuery.getSingleResult();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .items(mappedUsers)
                .totalPage(totalRecord.intValue() / pageSize)
                .build();
    }

    @Override
    public PageResponse<User> searchUsersByCriteria(int pageNo, int pageSize, String searchCriteria) {
        return null;
    }

    /**
     * Advance search user by criterias
     *
     * @param offset
     * @param pageSize
     * @param sortBy
     * @param search
     * @return
     */
    public PageResponse<?> searchUserByCriteria(int offset, int pageSize, String sortBy, String address, String... search) {
        log.info("Search user with search={} and sortBy={}", search, sortBy);

        List<SearchCriteria> criteriaList = new ArrayList<>();

        if (search.length > 0) {
            Pattern pattern = Pattern.compile(SEARCH_OPERATOR);
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    criteriaList.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            }
        }

        if (StringUtils.hasLength(sortBy)) {
            Pattern pattern = Pattern.compile(SORT_BY);
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    criteriaList.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            }
        }

        List<User> users = getUsers(offset, pageSize, criteriaList, address, sortBy);

        Long totalElements = getTotalElements(criteriaList);
        Page<User> page = new PageImpl<>(users, PageRequest.of(offset, pageSize), totalElements);


        return PageResponse.<User>builder()
                .pageNo(offset)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(users)
                .build();
    }

    /**
     * Get all users with conditions
     *
     * @param offset
     * @param pageSize
     * @param criteriaList
     * @param sortBy
     * @return
     */
    private List<User> getUsers(int offset, int pageSize, List<SearchCriteria> criteriaList, String address, String sortBy) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);

        Predicate userPredicate = criteriaBuilder.conjunction();
        UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(userPredicate, criteriaBuilder, userRoot);

        if (StringUtils.hasLength(address)) {
            Join<Address, User> userAddressJoin = userRoot.join("addresses");
            Predicate addressPredicate = criteriaBuilder.equal(userAddressJoin.get("city"), address);
            query.where(userPredicate, addressPredicate);
        } else {
            criteriaList.forEach(searchConsumer);
            userPredicate = searchConsumer.getPredicate();
            query.where(userPredicate);
        }

        if (StringUtils.hasLength(sortBy)) {
            Pattern pattern = Pattern.compile(SORT_BY);
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                String fieldName = matcher.group(1);
                String direction = matcher.group(3);
                if (direction.equalsIgnoreCase("asc")) {
                    query.orderBy(criteriaBuilder.asc(userRoot.get(fieldName)));
                } else {
                    query.orderBy(criteriaBuilder.desc(userRoot.get(fieldName)));
                }
            }
        }

        return entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();
    }

    /**
     * Count users with conditions
     *
     * @param params
     * @return
     */
    private Long getTotalElements(List<SearchCriteria> params) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<User> root = query.from(User.class);

        Predicate predicate = criteriaBuilder.conjunction();
        UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(predicate, criteriaBuilder, root);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.select(criteriaBuilder.count(root));
        query.where(predicate);

        return entityManager.createQuery(query).getSingleResult();
    }

}
