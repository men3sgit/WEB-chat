package vn.edu.nlu.fit.web.chat.services.impl;

import vn.edu.nlu.fit.web.chat.documents.Status;
import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.repositories.UserRepository;
import vn.edu.nlu.fit.web.chat.services.UserService;
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