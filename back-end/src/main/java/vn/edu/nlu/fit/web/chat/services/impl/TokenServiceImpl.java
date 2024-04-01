package vn.edu.nlu.fit.web.chat.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.nlu.fit.web.chat.documents.token.Token;
import vn.edu.nlu.fit.web.chat.documents.token.VerificationToken;
import vn.edu.nlu.fit.web.chat.exceptions.InvalidTokenException;
import vn.edu.nlu.fit.web.chat.repositoriy.TokenRepository;
import vn.edu.nlu.fit.web.chat.services.TokenService;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;


    @Override
    public boolean isTokenExpired(String token) {
        var storedToken = getToken(token);
        if (storedToken == null) {
            return true;
        }
        return Instant.now().isBefore(storedToken.getExpiryInstant());
    }

    @Override
    public Token getToken(String tokenValue) {
        return tokenRepository.findByTokenValue(tokenValue).orElseThrow(() -> new InvalidTokenException("Token invalid"));
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return null;
    }


}