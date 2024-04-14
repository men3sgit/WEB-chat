package vn.edu.nlu.fit.web.chat.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResetPasswordRequest {

    @NotEmpty(message = "Token cannot be empty")
    private String token;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 16, message = "Password must be between 6 and 16 characters")
    private String password;

    {
        "email": "user@example.com",
            "newPassword": "newPassword123",
            "resetToken": "resetTokenValue"
    }

}