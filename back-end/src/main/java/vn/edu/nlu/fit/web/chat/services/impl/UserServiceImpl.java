package vn.edu.nlu.fit.web.chat.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import vn.edu.nlu.fit.web.chat.documents.Status;
import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.documents.token.Token;
import vn.edu.nlu.fit.web.chat.documents.token.VerificationToken;
import vn.edu.nlu.fit.web.chat.dto.UserDto;
import vn.edu.nlu.fit.web.chat.dto.mapper.UserDtoMapper;
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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;

    private final UserDtoMapper userDtoMapper;


    @Override
    public void connect(UserDto user) {
        var connectingUser = getUserById(user.id());
        connectingUser.setStatus(Status.ONLINE);
        userRepository.save(connectingUser);
    }

    @Override
    public void disconnect(UserDto user) {
        var disconectingUser = getUserById(user.id());
        disconectingUser.setStatus(Status.OFFLINE);
        userRepository.save(disconectingUser);
    }

    @Override
    public List<UserDto> getConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE)
                .stream()
                .map(userDtoMapper)
                .toList();
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
            Token verificationToken = VerificationToken.builder()
                    .email(newUser.getEmail())
                    .expiry(Instant.now().plus(1, ChronoUnit.DAYS))
                    .value(UUID.randomUUID().toString())
                    .build();
            tokenService.save(verificationToken);
            emailService.sendVerificationEmail(newUser.getEmail(), verificationToken.getValue());
        } catch (InvalidTokenException e) {
            throw new ApiRequestException(e.getMessage());
        }

        return new RegistrationResponse(newUser.getId());
    }

    private User getUserById(String id) {
        return userRepository.findById(id)
                .orElse(null);
    }


}