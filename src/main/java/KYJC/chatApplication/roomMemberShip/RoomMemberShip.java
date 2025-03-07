package KYJC.chatApplication.roomMemberShip;

import KYJC.chatApplication.entity.Member;
import KYJC.chatApplication.entity.ChatRoom;
import jakarta.persistence.*;
import KYJC.chatApplication.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoomMemberShip extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;



    public RoomMemberShip(Member member, ChatRoom chatRoom) {
        super();
    }


}