package vn.edu.nlu.fit.web.chat.services;

public interface EmailService {
    void sendVerificationEmail(String email, String token);

}
