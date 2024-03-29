package vn.edu.nlu.fit.web.chat.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.nlu.fit.web.chat.payload.ApiResponse;
import vn.edu.nlu.fit.web.chat.payload.AuthenticationResponse;
import vn.edu.nlu.fit.web.chat.payload.LoginRequest;
import vn.edu.nlu.fit.web.chat.services.AuthenticationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        var res = authenticationService.login(username, password);
        return new ApiResponse<>(res);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authenticationService.logout();
        return ResponseEntity.noContent().build();
    }


}
