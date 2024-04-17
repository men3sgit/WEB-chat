package vn.edu.nlu.fit.web.chat.service;

import vn.edu.nlu.fit.web.chat.dto.request.ResetPasswordRequest;

public interface PasswordResetService {
    void sendPasswordReset(String username);

    void deletePasswordReset(String username);

    void resetPassword(ResetPasswordRequest request);
}
