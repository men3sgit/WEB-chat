package vn.edu.nlu.fit.web.chat.repositoriy;

import vn.edu.nlu.fit.web.chat.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByEmail(String email);
}