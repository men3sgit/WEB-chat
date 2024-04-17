package vn.edu.nlu.fit.web.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.nlu.fit.web.chat.dto.UserDto;
import vn.edu.nlu.fit.web.chat.dto.request.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.dto.response.RegistrationResponse;
import vn.edu.nlu.fit.web.chat.dto.response.ResponseSuccess;
import vn.edu.nlu.fit.web.chat.service.UserService;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Add new User", description = "API create a new user.")
    @PostMapping("/api/v1/users")
    public ResponseSuccess<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest request) {
        var data = userService.register(request);
        log.info("Registered user: {}", data);
        return new ResponseSuccess<>(HttpStatus.CREATED, "created new user", data);
    }

    @MessageMapping("/user.connect")
    @SendTo("/user/topic")
    public UserDto connect(@Payload UserDto user) {
        userService.connect(user);
        log.info("Connected user: {}", user);
        return user;
    }

    @MessageMapping("/user.disconnect")
    @SendTo("/user/topic")
    public UserDto disconnect(@Payload UserDto user) {
        userService.disconnect(user);
        log.info("Disconnected user: {}", user);
        return user;
    }


    @GetMapping("/api/v1/users/connected")
    public ResponseSuccess<List<UserDto>> getConnectedUsers() {
        var data = userService.getConnectedUsers();
        log.info("Connected users: {}", data);
        return new ResponseSuccess<>(HttpStatus.OK, "connected users", data);
    }


}