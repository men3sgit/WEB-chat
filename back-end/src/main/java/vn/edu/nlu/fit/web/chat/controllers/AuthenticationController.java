package vn.edu.nlu.fit.web.chat.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.nlu.fit.web.chat.dto.AuthenticationResponseDto;
import vn.edu.nlu.fit.web.chat.dto.LoginDto;
import vn.edu.nlu.fit.web.chat.services.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginDto loginDto) throws AuthenticationException {
        // Extract username and password from LoginDto
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        // Call Authentication Service for login attempt
        boolean isLogin = authenticationService.login(username, password);
        AuthenticationResponseDto response = new AuthenticationResponseDto(isLogin, "No message", null);

        // Handle successful login
        if (response.isSuccess()) {
            return ResponseEntity.ok(response); // Return OK (200) with response object
        } else {
            return ResponseEntity.badRequest().body(response); // Return Bad Request (400) with response object
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authenticationService.logout();
        return ResponseEntity.noContent().build();
    }
}
