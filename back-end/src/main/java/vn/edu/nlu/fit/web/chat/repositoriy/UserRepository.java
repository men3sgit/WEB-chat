package vn.edu.nlu.fit.web.chat.repositoriy;

import vn.edu.nlu.fit.web.chat.document.Status;
import vn.edu.nlu.fit.web.chat.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    List<User> findAllByStatus(Status status);
}