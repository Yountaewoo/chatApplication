package KYJC.chatApplication.service;

import KYJC.chatApplication.entity.ChatRoom;
import KYJC.chatApplication.entity.Member;
import KYJC.chatApplication.entity.RoomMemberShip;
import KYJC.chatApplication.repository.ChatRoomRepository;
import KYJC.chatApplication.repository.MemberRepository;
import KYJC.chatApplication.repository.RoomMemberShipRepository;
import KYJC.chatApplication.response.ChatRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    private final RoomMemberShipRepository roomMemberShipRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository, MemberRepository memberRepository, RoomMemberShipRepository roomMemberShipRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.memberRepository = memberRepository;
        this.roomMemberShipRepository = roomMemberShipRepository;
    }

    //로그인 아이디 확인
    public ChatRoomResponse createChatRoom(String name, String userid) {
        Member member = memberRepository.findByloginId(userid);

        ChatRoom chatRoom = ChatRoom.create(name);
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        roomMemberShipRepository.save(new RoomMemberShip(member, chatRoom));
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
