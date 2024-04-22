package vn.edu.nlu.fit.web.chat.repositoriy;

import vn.edu.nlu.fit.web.chat.dto.response.PageResponse;
import vn.edu.nlu.fit.web.chat.model.User;

public interface UserSearchRepository {

    PageResponse<?> searchUsersWithPaginationAndSorting(int pageNo, int pageSize, String search, String sortBy);

    PageResponse<?> searchUsersByCriteria(int pageNo, int pageSize, String searchCriteria);
}
