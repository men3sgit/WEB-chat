package vn.edu.nlu.fit.web.chat.document.token;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import vn.edu.nlu.fit.web.chat.document.AbstractEntity;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class VerificationToken extends AbstractEntity implements EmailToken {
    private String email;
    private Instant expiry;

    private String value;

    @Override
    public TokenType getTokenType() {
        return TokenType.VERIFICATION;
    }
}