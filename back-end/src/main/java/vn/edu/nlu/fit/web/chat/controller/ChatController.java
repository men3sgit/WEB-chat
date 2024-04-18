package vn.edu.nlu.fit.web.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import vn.edu.nlu.fit.web.chat.document.ChatMessage;
import vn.edu.nlu.fit.web.chat.document.ChatNotification;
import vn.edu.nlu.fit.web.chat.dto.response.ResponseSuccess;
import vn.edu.nlu.fit.web.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/messages/{sender}/{recipient}")
    public ResponseSuccess<List<ChatMessage>> getChatMessages(
            @PathVariable(name = "sender") Long senderId,
            @PathVariable(name = "recipient") Long recipientId) {

        var chatMessages = chatMessageService.getChatMessages(senderId, recipientId);
        log.info("show history of {} messages", chatMessages.size());
        return new ResponseSuccess<>(HttpStatus.OK,"chat message history",chatMessages);
    }

    @MessageMapping("/chat")
    public void processChatMessage(@Payload ChatMessage msg) {
        ChatMessage savedMsg = chatMessageService.save(msg);
        if (msg.getChatId() != null) { // Group chat message
            messagingTemplate.convertAndSend("/topic/chat/group/" + msg.getChatId(),
                    ChatNotification.builder()
                            .content(savedMsg.getContent())
                            .senderId(savedMsg.getSenderId())
                            .recipientId(savedMsg.getRecipientId()) // Consider omitting for group messages
                            .build());
        }
    }
}