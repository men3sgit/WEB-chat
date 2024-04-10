package vn.edu.nlu.fit.web.chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.nlu.fit.web.chat.payload.ApiResponse;
import vn.edu.nlu.fit.web.chat.payload.LoginResponse;
import vn.edu.nlu.fit.web.chat.payload.LoginRequest;
import vn.edu.nlu.fit.web.chat.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/sign-in")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        var res = authenticationService.login(email, password);
        return new ApiResponse<>(res);
    }


    @PostMapping("/sign-out")
    public ResponseEntity<Void> logout() {
        authenticationService.logout();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("verify-new-user")
    public ApiResponse<Void> verifyNewUserByToken(@RequestParam String token){
        authenticationService.verifyNewUser(token);
        return new ApiResponse<>();
    }


}
