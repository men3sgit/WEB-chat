package vn.edu.nlu.fit.web.chat.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomInfo {

    private String chatId;
    private Long creationTime; // Timestamp in milliseconds since epoch (or a suitable date/time object)
    private List<Long> participantUserIds; // List of user IDs participating in the chat room
    private String name; // Optional name for the chat room
    private String imageUrl; // Optional profile image URL for the chat room (if applicable)


}
