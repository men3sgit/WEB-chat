package vn.edu.nlu.fit.web.chat.services;

public interface EmailExtractorService {
    String extractEmailFromToken(String tokenValue);
}