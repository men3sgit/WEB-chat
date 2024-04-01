package vn.edu.nlu.fit.web.chat.documents.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken implements Token {
    private Long id;
    private String email;
    private Instant expiry;
    private String token;

    @Override
    public String getTokenValue() {
        return token;
    }

    @Override
    public void setTokenValue(String token) {
        this.token = token;
    }

    @Override
    public Instant getExpiryInstant() {
        return this.expiry;
    }

    @Override
    public void setExpiryInstant(Instant expiryInstant) {

    }

    @Override
    public TokenType getTokenType() {
        return TokenType.VERIFICATION;
    }
}