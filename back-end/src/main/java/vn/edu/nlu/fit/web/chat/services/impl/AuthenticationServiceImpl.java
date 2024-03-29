package vn.edu.nlu.fit.web.chat.services.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vn.edu.nlu.fit.web.chat.payload.AuthenticationResponse;
import vn.edu.nlu.fit.web.chat.exceptions.ApiRequestException;
import vn.edu.nlu.fit.web.chat.security.jwt.JwtService;
import vn.edu.nlu.fit.web.chat.services.AuthenticationService;
import vn.edu.nlu.fit.web.chat.utils.SecurityUtils;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public AuthenticationResponse login(String username, String password) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(username); // Check user exists
            var authentication = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(authentication);// check enable user or user locked
            LOGGER.info("Login successful for user: {}", username); // Log successful login

            String token = jwtService.generateToken(userDetails);

            return new AuthenticationResponse(token);
        } catch (Exception e) {
            // Catch any unexpected errors
            LOGGER.error("Unexpected error during login: ", e);  // Log for debugging
            throw new ApiRequestException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);  // Generic user-friendly message
        }
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null); // Clear authentication context
        SecurityContextHolder.clearContext(); // Optionally clear entire context
    }

    @Override
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    @Override
    public String getCurrentUsername() {
        return SecurityUtils.getCurrentUsername();
    }
}

