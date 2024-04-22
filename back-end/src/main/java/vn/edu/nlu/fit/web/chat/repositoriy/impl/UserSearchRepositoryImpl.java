package vn.edu.nlu.fit.web.chat.repositoriy.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import vn.edu.nlu.fit.web.chat.dto.mapper.UserDtoMapper;
import vn.edu.nlu.fit.web.chat.dto.response.PageResponse;
import vn.edu.nlu.fit.web.chat.model.User;
import vn.edu.nlu.fit.web.chat.repositoriy.UserSearchRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserSearchRepositoryImpl implements UserSearchRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final UserDtoMapper userDtoMapper;

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
                .totalPage(totalRecord.intValue()/pageSize)
                .build();
    }

    @Override
    public PageResponse<User> searchUsersByCriteria(int pageNo, int pageSize, String searchCriteria) {
        return null;
    }
}
