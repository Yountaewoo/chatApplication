package KYJC.chatApplication.service;

import KYJC.chatApplication.entity.ChatRoom;
import KYJC.chatApplication.repository.ChatRoomRepository;
import KYJC.chatApplication.response.ChatRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public ChatRoomResponse createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        return new ChatRoomResponse(savedChatRoom.getId(), savedChatRoom.getName());
    }

    public ChatRoomResponse updateChatRoom(Long chatRoomId, String name) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));
        chatRoom.updateName(name);
        ChatRoom updatedChatRoom = chatRoomRepository.save(chatRoom);
        return new ChatRoomResponse(updatedChatRoom.getId(), updatedChatRoom.getName());
    }
}
