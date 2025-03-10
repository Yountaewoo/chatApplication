package KYJC.chatApplication.repository;

import KYJC.chatApplication.entity.ChatRoom;
import KYJC.chatApplication.entity.Member;
import KYJC.chatApplication.entity.RoomMemberShip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomMemberShipRepository extends JpaRepository<RoomMemberShip, Long> {
    RoomMemberShip findByMemberAndChatRoom(Member member, ChatRoom chatRoom);

    List<RoomMemberShip> findByChatRoom(ChatRoom chatRoom);
}