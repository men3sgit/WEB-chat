package vn.edu.nlu.fit.web.chat.document.token;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;
@Entity
@Table(name = "tokens")
public interface Token {

    String getValue();      // Renamed for clarity

    void setValue(String token);

    Instant getExpiry();  // Modernized using Instant

    void setExpiry(Instant expiryInstant);

    TokenType getTokenType();    // Assuming TokenType is an enum


}