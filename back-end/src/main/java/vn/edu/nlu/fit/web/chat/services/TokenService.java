package vn.edu.nlu.fit.web.chat.services;


import vn.edu.nlu.fit.web.chat.documents.token.Token;

import java.time.LocalDateTime;

public interface TokenService {

    String generateToken(Token token);

    LocalDateTime calculateExpiryDate(int hours);

    boolean isTokenExpired(String token);

    Token getToken(String token);

    void saveToken(Token token);
}