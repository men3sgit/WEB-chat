package vn.edu.nlu.fit.web.chat.documents.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken implements Token, EmailToken {
    @Id
    private String id;
    private String email;
    private Instant expiry;
    private String value;

    @Override
    public TokenType getTokenType() {
        return TokenType.VERIFICATION;
    }
}