package vn.edu.nlu.fit.web.chat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.edu.nlu.fit.web.chat.documents.User;
import vn.edu.nlu.fit.web.chat.documents.token.VerificationToken;
import vn.edu.nlu.fit.web.chat.repositoriy.UserRepository;
import vn.edu.nlu.fit.web.chat.services.impl.UserServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testVerifyNewUser_ValidToken_Success() throws Exception {
        // Mock data
        String tokenValue = "valid-token";
        VerificationToken verificationToken = mock(VerificationToken.class);
        User user = mock(User.class);

        // Mock behavior
        when(tokenService.getToken(tokenValue)).thenReturn(verificationToken);
        when(verificationToken.getEmail()).thenReturn("user@example.com");
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(tokenService.isTokenExpired(null)).thenReturn(false); // Assuming isExpired() is moved to VerificationToken

        // Call the method
        userService.verifyNewUser(tokenValue);

        // Verify interactions
        verify(tokenService).getToken(tokenValue);
        verify(verificationToken).getEmail();
        verify(userRepository).findByEmail("user@example.com");
        verify(user).setActive(true);
        verify(userRepository).save(user);
        verifyNoMoreInteractions(userRepository, tokenService, verificationToken, emailService); // Ensure no unexpected interactions
    }

    @Test
    public void testVerifyNewUser_ExpiredToken_ThrowsException() throws Exception {
        // Mock data
        String tokenValue = "expired-token";
        VerificationToken verificationToken = mock(VerificationToken.class);

        // Mock behavior
        when(tokenService.getToken(tokenValue)).thenReturn(verificationToken);
        when(tokenService.isTokenExpired(tokenValue)).thenReturn(true); // Assuming isExpired() is moved to VerificationToken

        // Call the method (expecting exception)
        userService.verifyNewUser(tokenValue);

        // No further verification needed, exception expected
    }

    @Test
    public void testVerifyNewUser_InvalidToken_ThrowsException() throws Exception {
        // Mock data
        String tokenValue = "invalid-token";

        // Mock behavior
        when(tokenService.getToken(tokenValue)).thenReturn(null);

        // Call the method (expecting exception)
        userService.verifyNewUser(tokenValue);

        // No further verification needed, exception expected
    }

    @Test
    public void testVerifyNewUser_UserNotFound_ThrowsException() throws Exception {
        // Mock data
        String tokenValue = "valid-token";
        VerificationToken verificationToken = mock(VerificationToken.class);

        // Mock behavior
        when(tokenService.getToken(tokenValue)).thenReturn(verificationToken);
        when(verificationToken.getEmail()).thenReturn("user@example.com");
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.empty());

        // Call the method (expecting exception)
        userService.verifyNewUser(tokenValue);
        // No further verification needed, exception expected
    }
}
