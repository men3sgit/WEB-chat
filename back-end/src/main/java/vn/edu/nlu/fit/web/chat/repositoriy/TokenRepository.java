package vn.edu.nlu.fit.web.chat.repositoriy;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.nlu.fit.web.chat.documents.token.Token;

import java.util.Optional;

@Repository
public interface TokenRepository<T extends Token> extends MongoRepository<T, String> {

    Optional<T> findByValue(String value);
}
