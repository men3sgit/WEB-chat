package vn.edu.nlu.fit.web.chat.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.nlu.fit.web.chat.dto.UserDto;
import vn.edu.nlu.fit.web.chat.payload.ApiResponse;
import vn.edu.nlu.fit.web.chat.payload.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.payload.RegistrationResponse;
import vn.edu.nlu.fit.web.chat.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public ApiResponse<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest request) {
        var response = userService.register(request);
        return new ApiResponse<>(response);
    }

    @MessageMapping("/user.connect")
    @SendTo("/user/topic")
    public UserDto connect(@Payload UserDto user) {
        userService.connect(user);
        return user;
    }

    @MessageMapping("/user.disconnect")
    @SendTo("/user/topic")
    public UserDto disconnect(@Payload UserDto user) {
        userService.disconnect(user);
        return user;
    }


    @GetMapping("/api/v1/users/connected")
    public ResponseEntity<List<UserDto>> getConnectedUsers() {
        return ResponseEntity.ok(
                userService.getConnectedUsers()
        );
    }


}