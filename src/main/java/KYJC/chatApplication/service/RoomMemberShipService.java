package KYJC.chatApplication.service;

import KYJC.chatApplication.entity.Member;
import KYJC.chatApplication.entity.RoomMemberShip;
import KYJC.chatApplication.repository.MemberRepository;
import KYJC.chatApplication.entity.ChatRoom;
import KYJC.chatApplication.repository.ChatRoomRepository;
import KYJC.chatApplication.repository.RoomMemberShipRepository;
import KYJC.chatApplication.request.RoomMemberShipRequest;
import KYJC.chatApplication.response.RoomMemberShipResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoomMemberShipService {

    private final RoomMemberShipRepository repository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public RoomMemberShipService(RoomMemberShipRepository repository, MemberRepository memberRepository, ChatRoomRepository chatRoomRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

//    public List<RoomMemberShip> findAll() {
//        return repository.findAll();
//    }

//    public Optional<RoomMemberShip> findById(Long id) {
//        return repository.findById(id);
//    }

//    public RoomMemberShip save(RoomMemberShip roomMemberShip) {
//        return repository.save(roomMemberShip);
//    }

    //    public void deleteById(Long id) {
//        repository.deleteById(id);
//    }
    @Transactional
    public RoomMemberShipResponse inviteMemberToChatRoom(RoomMemberShipRequest roomMemberShipRequest) {
        Member member = memberRepository.findById(roomMemberShipRequest.getMemberId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자가 없습니다."));
        ChatRoom chatRoom = chatRoomRepository.findById(roomMemberShipRequest.getChatRoomId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 채팅방이 없습니다."));
        RoomMemberShip save = repository.save(new RoomMemberShip(member, chatRoom));
        return new RoomMemberShipResponse(
                save.getId(),
                save.getMember().getId(),
                save.getChatRoom().getId());
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
    }

    public ChatRoom findChatRoomById(Long id) {
        return chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
    }

    //사용자id, 채팅방id
    public void leaveRoomMemberShip(String userId, Long chatroomId){
        Member member = memberRepository.findByloginId(userId);
        if (member ==null){
            throw new IllegalArgumentException(("해당 사용자가 없습니다"));
        }
        ChatRoom chatRoom = chatRoomRepository.findById(chatroomId).orElseThrow(()-> new IllegalArgumentException("해당 채팅방 없음"));

        //해당 채팅방에 들어가있는 회원인지 확인
        RoomMemberShip roomMemberShip = repository.findByMemberAndChatRoom(member, chatRoom);

        //채팅방 나가기
        repository.delete(roomMemberShip);

        // 둘다 나갔을때 채팅방 완전 삭제
        List<RoomMemberShip> remainingMembers = repository.findByChatRoom(chatRoom);
        if(remainingMembers.isEmpty()){
            chatRoom.markAsDeleted();
            chatRoomRepository.save(chatRoom);
        }




    }
}