package vn.edu.nlu.fit.web.chat.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.nlu.fit.web.chat.document.token.Token;

import java.util.Optional;

@Repository
public interface TokenRepository<T extends Token> extends JpaRepository<T, Long> {

    Optional<T> findByValue(String value);
}
