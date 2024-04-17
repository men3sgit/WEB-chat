package vn.edu.nlu.fit.web.chat.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ChatMessage {
    @Id
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
    private String content;
    private LocalDateTime timestamp;
}