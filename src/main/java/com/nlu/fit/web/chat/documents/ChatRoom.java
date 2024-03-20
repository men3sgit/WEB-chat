package com.nlu.fit.web.chat.documents;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class ChatRoom {
    @Id
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;

}