package vn.edu.nlu.fit.web.chat.services;


import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.payload.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.payload.RegistrationResponse;

import java.util.List;

public interface UserService {
    void connect(User user);

    void disconnect(User user);

    List<User> getConnectedUsers();

    RegistrationResponse register(RegistrationRequest request);

    void verifyAccount(String token);


}