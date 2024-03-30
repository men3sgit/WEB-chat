package vn.edu.nlu.fit.web.chat.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import vn.edu.nlu.fit.web.chat.documents.Status;
import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.documents.token.Token;
import vn.edu.nlu.fit.web.chat.documents.token.VerificationToken;
import vn.edu.nlu.fit.web.chat.exceptions.ApiRequestException;
import vn.edu.nlu.fit.web.chat.exceptions.InvalidTokenException;
import vn.edu.nlu.fit.web.chat.payload.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.payload.RegistrationResponse;
import vn.edu.nlu.fit.web.chat.repositoriy.UserRepository;
import vn.edu.nlu.fit.web.chat.services.EmailService;
import vn.edu.nlu.fit.web.chat.services.TokenService;
import vn.edu.nlu.fit.web.chat.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.nlu.fit.web.chat.utils.SpringDataUtil;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private final EmailService emailService;

    @Override
    public void connect(User user) {
        user.setId(10L);
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    @Override
    public void disconnect(User user) {
        var storedUser = getUserById(user.getId());
        if (storedUser == null) {
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
        var newUser = SpringDataUtil.copyProperties(request, User.class);
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        // TODO: create new token then send
        try {
            Token verificationToken = VerificationToken.builder().build();
            String tokenStr = tokenService.generateToken(verificationToken);
            emailService.sendVerificationEmail(newUser.getEmail(), tokenStr);
            tokenService.saveToken(verificationToken);
        } catch (InvalidTokenException e) {
            Logger.getLogger("s");
        }

        return new RegistrationResponse(newUser.getId());
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }
    @Override
    public void verifyAccount(String token) {
        Token verificationToken = tokenService.getToken(token);
        if (tokenService.isTokenExpired()) { // Implement isExpired() in Token
            throw new InvalidTokenException("Verification token expired!");
        }

        User user = verificationToken.getUser(); // Assuming Token has getUser()
        user.setVerified(true);
        userRepository.save(user);
    }

}