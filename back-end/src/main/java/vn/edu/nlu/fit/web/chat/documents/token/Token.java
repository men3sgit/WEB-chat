package vn.edu.nlu.fit.web.chat.documents.token;

public interface Token {

    String getToken();

    void setToken(String token);

    long getExpiryDate();

    void setExpiryDate(long expiryDate);


}