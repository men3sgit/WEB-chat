package vn.edu.nlu.fit.web.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vn.edu.nlu.fit.web.chat.document.User;
import vn.edu.nlu.fit.web.chat.document.token.Token;
import vn.edu.nlu.fit.web.chat.dto.request.ResetPasswordRequest;
import vn.edu.nlu.fit.web.chat.dto.response.LoginResponse;
import vn.edu.nlu.fit.web.chat.exception.ApiRequestException;
import vn.edu.nlu.fit.web.chat.repositoriy.UserRepository;
import vn.edu.nlu.fit.web.chat.security.jwt.JwtService;
import vn.edu.nlu.fit.web.chat.service.AuthenticationService;
import vn.edu.nlu.fit.web.chat.service.EmailExtractorService;
import vn.edu.nlu.fit.web.chat.service.TokenService;
import vn.edu.nlu.fit.web.chat.utils.SpringSecurityUtil;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final TokenService tokenService;

    private final EmailExtractorService emailExtractorService;

    private final UserRepository userRepository;

    @Override
    public LoginResponse login(String email, String password) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(email); // Check user exists
            var authentication = new UsernamePasswordAuthenticationToken(email, password);
            authenticationManager.authenticate(authentication);// check enable user or user locked
            LOGGER.info("Login successful for user: {}", email); // Log successful login

            String token = jwtService.generateToken(userDetails);

            return new LoginResponse(token);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());  // Generic user-friendly message
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
        return SpringSecurityUtil.getCurrentUsername();
    }

    @Override
    public void verifyNewUser(String tokenValue) {
        try {
            Token verificationToken = tokenService.getToken(tokenValue);

            if (tokenService.isTokenExpired(verificationToken)) {
                throw new ApiRequestException("Token expired");
            }
            String email = emailExtractorService.extractEmailFromToken(verificationToken.getValue()); // Changed to getTokenValue()
            User user = userRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("Email not found"));
            user.setActive(true);
            userRepository.save(user);
            // ERROR duplicate token id if not delete
            tokenService.delete(verificationToken);
        } catch (RuntimeException ex) {
            throw new ApiRequestException(ex.getMessage());
        }
    }

    @Override
    public void sendPasswordReset(String username) {

    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {

    }
}

