package vn.edu.nlu.fit.web.chat.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}
