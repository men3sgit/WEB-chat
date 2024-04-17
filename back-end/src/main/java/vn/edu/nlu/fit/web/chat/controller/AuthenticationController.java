package vn.edu.nlu.fit.web.chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.nlu.fit.web.chat.dto.request.ResetPasswordRequest;
import vn.edu.nlu.fit.web.chat.dto.response.ApiResponse;
import vn.edu.nlu.fit.web.chat.dto.response.LoginResponse;
import vn.edu.nlu.fit.web.chat.dto.request.LoginRequest;
import vn.edu.nlu.fit.web.chat.dto.response.ResponseSuccess;
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
    public ResponseSuccess<Void> logout() {
        authenticationService.logout();
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT,"Logout successful");
    }

    @GetMapping("verify-new-user")
    public ResponseSuccess<Void> verifyNewUserByToken(@RequestParam String token){
        authenticationService.verifyNewUser(token);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT,"Verify new user successful");
    }

    @PostMapping("/forgot-password")
    public ResponseSuccess<Void> forgotPassword(@RequestParam String username) {
        authenticationService.sendPasswordReset(username);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT,"Forgot password reset successful");
    }

    @PatchMapping("/reset-password")
    public ResponseSuccess<Void> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        authenticationService.resetPassword(request);
        return new ResponseSuccess<>(HttpStatus.ACCEPTED,"Reset password successful");
    }


}
