package vn.edu.nlu.fit.web.chat.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.nlu.fit.web.chat.document.token.Token;
import vn.edu.nlu.fit.web.chat.exception.InvalidTokenException;
import vn.edu.nlu.fit.web.chat.repositoriy.TokenRepository;
import vn.edu.nlu.fit.web.chat.service.TokenService;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository<Token> tokenRepository;


    @Override
    public boolean isTokenExpired(Token token) {
        return token.getExpiry().isBefore(Instant.now());
    }

    @Override
    public Token getToken(String tokenValue) {

        return tokenRepository.findByValue(tokenValue).orElseThrow(() -> new InvalidTokenException("Cannot found the token"));
    }

    @Override
    public void save(Token token) {
        tokenRepository.insert(token);
    }

    @Override
    public void delete(Token token) {
        tokenRepository.delete(token);
    }


}