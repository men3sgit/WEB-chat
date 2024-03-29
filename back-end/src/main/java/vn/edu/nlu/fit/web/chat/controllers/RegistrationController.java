package vn.edu.nlu.fit.web.chat.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.nlu.fit.web.chat.payload.ApiResponse;
import vn.edu.nlu.fit.web.chat.payload.RegistrationRequest;
import vn.edu.nlu.fit.web.chat.payload.RegistrationResponse;
import vn.edu.nlu.fit.web.chat.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/api/v1/users")
    public ApiResponse<RegistrationResponse> registerUser(@Validated @RequestBody RegistrationRequest request) {
        var response = userService.register(request);
        return new ApiResponse<>(response);
    }
}
