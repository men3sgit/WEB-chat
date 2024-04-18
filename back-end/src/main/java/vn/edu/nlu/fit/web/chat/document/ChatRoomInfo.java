package vn.edu.nlu.fit.web.chat.document;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "chat_room_info")
public class ChatRoomInfo extends AbstractEntity {

    @Column(name = "chat_id", nullable = false)
    private String chatId;

    @Column(name = "name")
    private String name; // Optional name for the chat room

    @Column(name = "image_url")
    private String imageUrl; // Optional profile image URL for the chat room (if applicable)
}
