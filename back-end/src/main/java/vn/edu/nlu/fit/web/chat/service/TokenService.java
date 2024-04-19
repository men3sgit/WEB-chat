package vn.edu.nlu.fit.web.chat.service;


import vn.edu.nlu.fit.web.chat.model.token.Token;

public interface TokenService {

    boolean isTokenExpired(Token token);

    Token getToken(String tokenValue);

    void save(Token token);

    void delete(Token token);
}