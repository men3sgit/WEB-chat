package com.nlu.fit.web.chat.services;


import com.nlu.fit.web.chat.documents.User;

import java.util.List;

public interface UserService {
    void connect(User user);
    void disconnect(User user);
    List<User> getConnectedUsers();

}