package vn.edu.nlu.fit.web.chat.services.impl;

import vn.edu.nlu.fit.web.chat.documents.Status;
import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.exceptions.ApiRequestException;
import vn.edu.nlu.fit.web.chat.payload.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.payload.RegistrationResponse;
import vn.edu.nlu.fit.web.chat.repositoriy.UserRepository;
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

    @Override
    public RegistrationResponse register(RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ApiRequestException("Email taken!");
        }
        // create new user
        var newUser = DataUtils.copyProperties(request, User.class);
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        //create new account
        String appName = request.getEmail().substring(0, request.getEmail().indexOf('@'));
        var accountCreateRequest = AccountCreateRequest.builder()
                .userId(newUser.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .appName(appName)
                .phoneNumber(request.getPhoneNumber())
                .build();
        accountService.create(accountCreateRequest);

        // create new token
        var tokenCreateResponse = tokenService.createRegistrationToken(TokenCreateRequest.of(newUser.getId()));
        tokenService.sendMailToVerify(request.getEmail(), accountCreateRequest.getFirstName(), tokenCreateResponse.getToken());

        return RegistrationResponse.of(newUser.getId());
    }

    private User getUserById(Long id){
        return userRepository.findById(id)
                .orElse(null);
    }
}