package com.nlu.fit.web.chat.services.impl;

import com.nlu.fit.web.chat.documents.Status;
import com.nlu.fit.web.chat.documents.User;
import com.nlu.fit.web.chat.repositories.UserRepository;
import com.nlu.fit.web.chat.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void connect(User user) {
        user.setId(10L);
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    @Override
    public void disconnect(User user) {
        var storedUser = getUserById(user.getId());
        if(storedUser == null){
            // TODO: can implement needed requirements
            return;
        }


        user.setStatus(Status.OFFLINE);
        userRepository.save(user);
    }

    @Override
    public List<User> getConnectedUsers() {
        return null;
    }

    private User getUserById(Long id){
        return userRepository.findById(id)
                .orElse(null);
    }
}