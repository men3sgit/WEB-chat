package vn.edu.nlu.fit.web.chat.documents.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken implements Token {
    private Long id;
    private Long userId;
    private long expiry;
    private String token;

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public long getExpiryDate() {
        return this.expiry;
    }

    @Override
    public void setExpiryDate(long expiryDate) {
        this.expiry = expiryDate;
    }
}