package vn.edu.nlu.fit.web.chat.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.edu.nlu.fit.web.chat.service.EmailService;

@RequiredArgsConstructor
@Service
public class SmtpEmailService implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;


    @Value("${app.baseURL}")
    private String baseURL;

    @Async
    @Override
    public void sendVerificationNewUser(String email, String token) {
        String subject = "Please verify your account";
        String verificationUrl = baseURL + "/api/v1/auth/verify-new-user?token=" + token;
        String message = "Click the link below to verify your email address:\n\n\n" + verificationUrl;

        MimeMessage mimeMessage = createMimeMessage(email, subject, message);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendResetPassword(String email, String resetPasswordToken) {

    }

    private MimeMessage createMimeMessage(String email, String subject, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setPriority(1);
            helper.setSubject(subject);
            helper.setFrom(from);
            helper.setTo(email); // Use the email parameter here
            helper.setText(message);
            return mimeMessage; // Don't call send here
        } catch (MailException e) {
            // Handle specific mail exceptions here
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
