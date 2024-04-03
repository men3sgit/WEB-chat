package vn.edu.nlu.fit.web.chat.documents.token;

import java.time.Instant;

public interface Token {

        String getValue();      // Renamed for clarity

        void setValue(String token);

        Instant getExpiry();  // Modernized using Instant

        void setExpiry(Instant expiryInstant);

        TokenType getTokenType();    // Assuming TokenType is an enum


}