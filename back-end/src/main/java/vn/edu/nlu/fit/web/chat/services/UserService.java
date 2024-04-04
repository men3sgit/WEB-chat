package vn.edu.nlu.fit.web.chat.services;


import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.dto.UserDto;
import vn.edu.nlu.fit.web.chat.payload.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.payload.RegistrationResponse;

import java.util.List;

public interface UserService {
    void connect(UserDto user);

    void disconnect(UserDto user);

    List<UserDto> getConnectedUsers();

    RegistrationResponse register(RegistrationRequest request);



}