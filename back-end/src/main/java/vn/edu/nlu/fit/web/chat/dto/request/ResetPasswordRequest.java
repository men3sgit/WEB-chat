package vn.edu.nlu.fit.web.chat.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @example:
 *          {
 *               "email": "user@example.com",
 *               "newPassword": "newPassword123",
 *               "resetToken": "resetTokenValue"
 *          }
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResetPasswordRequest {
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;


    @NotEmpty(message = "Token cannot be empty")
    private String resetToken;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 16, message = "Password must be between 6 and 16 characters")
    private String newPassword;


}