package vn.edu.nlu.fit.web.chat.documents.token;

import java.time.Instant;

public interface Token {

        String getTokenValue();      // Renamed for clarity

        void setTokenValue(String token);

        Instant getExpiryInstant();  // Modernized using Instant

        void setExpiryInstant(Instant expiryInstant);

        TokenType getTokenType();    // Assuming TokenType is an enum


}