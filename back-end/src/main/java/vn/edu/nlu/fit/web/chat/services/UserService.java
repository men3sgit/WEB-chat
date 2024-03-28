package vn.edu.nlu.fit.web.chat.services;


import vn.edu.nlu.fit.web.chat.documents.User;

import java.util.List;

public interface UserService {
    void connect(User user);
    void disconnect(User user);
    List<User> getConnectedUsers();

}