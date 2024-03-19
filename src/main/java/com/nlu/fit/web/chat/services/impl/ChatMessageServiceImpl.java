package com.nlu.fit.web.chat.services.impl;

import com.nlu.fit.web.chat.documents.ChatMessage;
import com.nlu.fit.web.chat.repositories.ChatMessageRepository;
import com.nlu.fit.web.chat.services.ChatMessageService;
import com.nlu.fit.web.chat.services.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}