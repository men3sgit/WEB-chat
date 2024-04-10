package vn.edu.nlu.fit.web.chat.service;

public interface EmailExtractorService {
    String extractEmailFromToken(String tokenValue);
}