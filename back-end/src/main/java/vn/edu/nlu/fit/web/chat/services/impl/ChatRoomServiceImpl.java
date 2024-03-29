package vn.edu.nlu.fit.web.chat.services.impl;

import vn.edu.nlu.fit.web.chat.documents.ChatRoom;
import vn.edu.nlu.fit.web.chat.repositoriy.ChatRoomRepository;
import vn.edu.nlu.fit.web.chat.services.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private static final String CHAT_ID_FORMAT = "%s_%s";

    @Override
    public Optional<String> getChatId(Long senderId, Long recipientId, boolean createNewRoomIfNotExists) {
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);

        if (existingChatRoom.isPresent()) {
            return existingChatRoom.map(ChatRoom::getChatId);
        }

        if (createNewRoomIfNotExists) {
            return createChatId(senderId, recipientId);
        }

        return Optional.empty();
    }

    private Optional<String> createChatId(Long senderId, Long recipientId) {
        String chatId = String.format(CHAT_ID_FORMAT, senderId, recipientId);
        createNewChatRoom(senderId, recipientId, chatId);
        createNewChatRoom(recipientId, senderId, chatId);

        return Optional.of(chatId);
    }

    private void createNewChatRoom(Long senderId, Long recipientId, String chatId) {
        ChatRoom newChatRoom = ChatRoom.builder()
                .senderId(senderId)
                .recipientId(recipientId)
                .chatId(chatId).build();

        chatRoomRepository.save(newChatRoom);
    }
}