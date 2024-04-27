package vn.edu.nlu.fit.web.chat.service;


import vn.edu.nlu.fit.web.chat.dto.UserDto;
import vn.edu.nlu.fit.web.chat.dto.request.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.dto.request.UpdateUserRequest;
import vn.edu.nlu.fit.web.chat.dto.response.PageResponse;
import vn.edu.nlu.fit.web.chat.dto.response.RegistrationResponse;

import java.util.List;

public interface UserService {
    void connect(UserDto user);

    void disconnect(UserDto user);

    List<UserDto> getConnectedUsers();

    RegistrationResponse register(RegistrationRequest request);

    String getNameByEmail(String email);

    PageResponse<?> getAllUsersAndSearchWithPagingAndSorting(int pageNo, int pageSize, String search, String sortBy);

    void updateUser(Long userId, UpdateUserRequest request);
}