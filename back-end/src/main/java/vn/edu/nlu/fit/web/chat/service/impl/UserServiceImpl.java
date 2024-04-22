package vn.edu.nlu.fit.web.chat.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import vn.edu.nlu.fit.web.chat.dto.response.PageResponse;
import vn.edu.nlu.fit.web.chat.enums.UserStatus;
import vn.edu.nlu.fit.web.chat.model.User;
import vn.edu.nlu.fit.web.chat.model.token.Token;
import vn.edu.nlu.fit.web.chat.dto.UserDto;
import vn.edu.nlu.fit.web.chat.dto.mapper.UserDtoMapper;
import vn.edu.nlu.fit.web.chat.exception.ApiRequestException;
import vn.edu.nlu.fit.web.chat.exception.InvalidTokenException;
import vn.edu.nlu.fit.web.chat.exception.UserNotFoundException;
import vn.edu.nlu.fit.web.chat.dto.request.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.dto.response.RegistrationResponse;
import vn.edu.nlu.fit.web.chat.model.token.TokenType;
import vn.edu.nlu.fit.web.chat.repositoriy.UserRepository;
import vn.edu.nlu.fit.web.chat.repositoriy.UserSearchRepository;
import vn.edu.nlu.fit.web.chat.service.EmailService;
import vn.edu.nlu.fit.web.chat.service.TokenService;
import vn.edu.nlu.fit.web.chat.service.UserService;
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

    private final UserSearchRepository userSearchRepository;


    @Override
    public void connect(UserDto user) {
        var connectingUser = getUserById(user.id());
        connectingUser.setUserStatus(UserStatus.ONLINE);
        userRepository.save(connectingUser);
    }

    @Override
    public void disconnect(UserDto user) {
        var disconectingUser = getUserById(user.id());
        disconectingUser.setUserStatus(UserStatus.OFFLINE);
        userRepository.save(disconectingUser);
    }

    @Override
    public List<UserDto> getConnectedUsers() {
        return userRepository.findAllByUserStatus(UserStatus.ONLINE).stream().map(userDtoMapper).toList();
    }

    @Override
    public RegistrationResponse register(RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ApiRequestException("Email taken!");
        }
        // create new user
        var newUser = SpringDataUtil.copyProperties(request, User.class);
        newUser.setUserStatus(UserStatus.OFFLINE);
        newUser.setUsername(request.getEmail().split("@")[0]);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        try {
            Token verificationToken = Token.builder()
                    .owner(newUser.getId())
                    .type(TokenType.VERIFICATION)
                    .expiredAt(Instant.now().plus(1, ChronoUnit.DAYS))
                    .value(UUID.randomUUID().toString()).build();
            tokenService.save(verificationToken);
            emailService.sendVerificationNewUser(newUser.getEmail(), verificationToken.getValue());
        } catch (InvalidTokenException e) {
            throw new ApiRequestException(e.getMessage());
        }

        return new RegistrationResponse(newUser.getId());
    }

    @Override
    public String getNameByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Email invalid")).getFirstName();
    }

    @Override
    public PageResponse<?> getAllUsersAndSearchWithPagingAndSorting(int pageNo, int pageSize, String search, String sortBy) {
        return userSearchRepository.searchUsersWithPaginationAndSorting(pageNo, pageSize, search, sortBy);

    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


}