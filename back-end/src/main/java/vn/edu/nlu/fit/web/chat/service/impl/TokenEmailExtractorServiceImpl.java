package vn.edu.nlu.fit.web.chat.service.impl;

import lombok.RequiredArgsConstructor;
import vn.edu.nlu.fit.web.chat.document.token.EmailToken;
import vn.edu.nlu.fit.web.chat.document.token.Token;
import vn.edu.nlu.fit.web.chat.service.EmailExtractorService;
import vn.edu.nlu.fit.web.chat.service.TokenService;
import vn.edu.nlu.fit.web.chat.exception.ApiRequestException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class TokenEmailExtractorServiceImpl implements EmailExtractorService {

    private final TokenService tokenService;

    @Override
    public String extractEmailFromToken(String tokenValue) {
        try {
            Token token = tokenService.getToken(tokenValue);
            if (token instanceof EmailToken) {
                return ((EmailToken) token).getEmail();
            } else {
                throw new ApiRequestException("Token does not contain email information");
            }
        } catch (Exception e) {
            throw new ApiRequestException("Error extracting email from token: " + e.getMessage());
        }
    }
}
