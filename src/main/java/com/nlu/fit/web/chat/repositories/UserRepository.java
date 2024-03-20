package com.nlu.fit.web.chat.repositories;

import com.nlu.fit.web.chat.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
}