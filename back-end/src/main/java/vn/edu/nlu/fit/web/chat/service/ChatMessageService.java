
package vn.edu.nlu.fit.web.chat.service;

import vn.edu.nlu.fit.web.chat.document.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    ChatMessage save(ChatMessage message);

    List<ChatMessage> getChatMessages(Long senderId, Long recipientId);
}
