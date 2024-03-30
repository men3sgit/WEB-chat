    package vn.edu.nlu.fit.web.chat.services.impl;


    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import vn.edu.nlu.fit.web.chat.documents.token.Token;
    import vn.edu.nlu.fit.web.chat.exceptions.InvalidTokenException;
    import vn.edu.nlu.fit.web.chat.repositoriy.TokenRepository;
    import vn.edu.nlu.fit.web.chat.services.TokenService;

    import java.time.LocalDateTime;
    import java.util.UUID;

    @Service
    @RequiredArgsConstructor
    public class TokenServiceImpl implements TokenService {
        private final TokenRepository tokenRepository;

        @Override
        public String generateToken(Token token) {
            token.setToken(UUID.randomUUID().toString());
            return token.getToken();
        }

        @Override
        public LocalDateTime calculateExpiryDate(int hours) {
            return null;
        }

        @Override
        public boolean isTokenExpired(String token) {
            return false;
        }

        @Override
        public Token getToken(String token) {
            return tokenRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException("Token invalid"));
        }

        @Override
        public void saveToken(Token token) {
            tokenRepository.save(token);
        }
    }