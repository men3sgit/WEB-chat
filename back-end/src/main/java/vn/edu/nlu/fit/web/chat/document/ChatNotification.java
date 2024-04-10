package vn.edu.nlu.fit.web.chat.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ChatNotification {
    @Id
    private Long id;
    private Long senderId;
    private Long recipientId;
    private String content;
}