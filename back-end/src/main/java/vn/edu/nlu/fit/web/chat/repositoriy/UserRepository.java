package vn.edu.nlu.fit.web.chat.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.nlu.fit.web.chat.document.UserStatus;
import vn.edu.nlu.fit.web.chat.document.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByStatus(UserStatus userStatus);
}