package vn.edu.nlu.fit.web.chat.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.nlu.fit.web.chat.payload.ApiResponse;
import vn.edu.nlu.fit.web.chat.payload.LoginResponse;
import vn.edu.nlu.fit.web.chat.payload.LoginRequest;
import vn.edu.nlu.fit.web.chat.services.AuthenticationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        var res = authenticationService.login(email, password);
        return new ApiResponse<>(res);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authenticationService.logout();
        return ResponseEntity.noContent().build();
    }


}
