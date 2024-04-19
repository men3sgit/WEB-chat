package vn.edu.nlu.fit.web.chat.service.impl;

import vn.edu.nlu.fit.web.chat.model.ChatMessage;
import vn.edu.nlu.fit.web.chat.repositoriy.ChatMessageRepository;
import vn.edu.nlu.fit.web.chat.service.ChatMessageService;
import vn.edu.nlu.fit.web.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    @Override
    public ChatMessage save(ChatMessage message) {
        String chatId = chatRoomService.getChatId(
                        message.getSenderId(),
                        message.getRecipientId(),
                        true
                )
                .orElseThrow();
        message.setChatId(chatId);
        chatMessageRepository.save(message);

        return message;
    }

    @Override
    public List<ChatMessage> getChatMessages(Long senderId, Long recipientId) {
        Optional<String> chatId = chatRoomService.getChatId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(Collections.EMPTY_LIST);
    }
}