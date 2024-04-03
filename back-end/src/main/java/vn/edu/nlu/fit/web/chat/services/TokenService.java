package vn.edu.nlu.fit.web.chat.services;


import vn.edu.nlu.fit.web.chat.documents.token.Token;
import vn.edu.nlu.fit.web.chat.documents.token.VerificationToken;

import java.time.LocalDateTime;

public interface TokenService {

    boolean isTokenExpired(Token token);

    Token getToken(String tokenValue);

    void save(Token token);

    void delete(Token token);
}