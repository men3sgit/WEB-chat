package vn.edu.nlu.fit.web.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.nlu.fit.web.chat.dto.request.LoginRequest;
import vn.edu.nlu.fit.web.chat.dto.request.ResetPasswordRequest;
import vn.edu.nlu.fit.web.chat.dto.response.LoginResponse;
import vn.edu.nlu.fit.web.chat.dto.response.ResponseError;
import vn.edu.nlu.fit.web.chat.dto.response.ResponseFailure;
import vn.edu.nlu.fit.web.chat.dto.response.ResponseSuccess;
import vn.edu.nlu.fit.web.chat.exception.ApiRequestException;
import vn.edu.nlu.fit.web.chat.exception.UserNotFoundException;
import vn.edu.nlu.fit.web.chat.service.AuthenticationService;

import javax.naming.AuthenticationException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Controller")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @Operation(
            summary = "Login",
            description = "Authenticate and log in a user."
    )
    @PostMapping(path = "/sign-in")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        log.info("Received login request for user: {}", email);
        try {
            var res = authenticationService.login(email, password);
            log.info("Login successful for user: {}", email);
            return new ResponseSuccess(HttpStatus.OK, "Login successful", res);
        } catch (UserNotFoundException e) {
            log.error("User not found for email: {}", email);
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ApiRequestException e) {
            log.error("Api request exception for user: {}", email);
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred during login for user: {}", email, e);
            return new ResponseFailure(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }


    @Operation(
            summary = "Logout",
            description = "Log out the currently authenticated user."
    )
    @PostMapping("/sign-out")
    public ResponseEntity<?> logout() {
        try {
            log.info("Received logout request");
            authenticationService.logout();
            log.info("Logout successful");
            return new ResponseSuccess<>(HttpStatus.NO_CONTENT, "Logout successful");
        } catch (Exception e) {
            log.error("An error occurred during logout: {}", e.getMessage());
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during logout");
        }
    }

    @Operation(
            summary = "Verify New User",
            description = "Verify a new user by token."
    )
    @GetMapping("verify-new-user")
    public ResponseEntity<?> verifyNewUserByToken(@RequestParam String token) {
        try {
            authenticationService.verifyNewUser(token);
            log.info("User verification successful for token: {}", token);
            return new ResponseSuccess(HttpStatus.NO_CONTENT, "Verify new user successful");
        }catch (ApiRequestException e){
            log.error("Api request exception for token: {}", token, e);
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e) {
            log.error("An error occurred during user verification for token {}: {}", token, e.getMessage());
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during user verification");
        }
    }

    @Operation(
            summary = "Forgot Password",
            description = "Initiate the password reset process for a user."
    )
    @PostMapping("/forgot-password")
    public ResponseSuccess<?> forgotPassword(@RequestParam String username) {
        try {
            log.info("Received forgot password request for user: {}", username);
            authenticationService.initiatePasswordResetProcess(username);
            log.info("Forgot password request successful for user: {}", username);
            return new ResponseSuccess<>(HttpStatus.NO_CONTENT, "Forgot password reset successful");
        } catch (Exception e) {
            log.error("An error occurred during forgot password process for user {}: {}", username, e.getMessage());
            return new ResponseFailure(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred during forgot password process");
        }
    }

    @Operation(
            summary = "Reset Password",
            description = "Reset the password for a user."
    )
    @PatchMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        String username = request.getEmail();
        log.info("Received password reset request for user: {}", username);
        try {
            authenticationService.resetPassword(request);
            log.info("Password reset successfully for user: {}", username);
            return new ResponseSuccess(HttpStatus.ACCEPTED, "Password reset successful");
        } catch (Exception e) {
            log.error("Error occurred during password reset for user {}: {}", username, e.getMessage());
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred during password reset for user " + username);
        }
    }


}
