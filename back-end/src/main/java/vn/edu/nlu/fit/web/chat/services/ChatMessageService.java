
package vn.edu.nlu.fit.web.chat.services;

import vn.edu.nlu.fit.web.chat.documents.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage save(ChatMessage message);

    List<ChatMessage> getChatMessages(Long senderId, Long recipientId);
}
