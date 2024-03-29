package vn.edu.nlu.fit.web.chat.repositoriy;

import vn.edu.nlu.fit.web.chat.documents.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage,Long> {
    List<ChatMessage> findByChatId(String chatId);
}