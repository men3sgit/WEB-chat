package vn.edu.nlu.fit.web.chat.document.token;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "tokens")
public interface Token {

        String getValue();      // Renamed for clarity

        void setValue(String token);

        Instant getExpiry();  // Modernized using Instant

        void setExpiry(Instant expiryInstant);

        TokenType getTokenType();    // Assuming TokenType is an enum


}