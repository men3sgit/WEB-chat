package vn.edu.nlu.fit.web.chat.service;

import vn.edu.nlu.fit.web.chat.model.ChatRoomInfo;

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