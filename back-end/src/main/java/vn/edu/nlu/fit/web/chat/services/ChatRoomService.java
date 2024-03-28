package vn.edu.nlu.fit.web.chat.services;

import vn.edu.nlu.fit.web.chat.documents.ChatRoomInfo;

import java.util.Optional;

public interface ChatRoomService {
    Optional<String> getChatId(
            Long senderId,
            Long recipientId,
            boolean createNewRoomIfNotExists
    );

    void addUserToChatRoom(String chatId, Long userId);

    Optional<ChatRoomInfo> getChatRoomInfo(String chatId);


}