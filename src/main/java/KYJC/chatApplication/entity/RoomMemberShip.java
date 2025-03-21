package KYJC.chatApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
//@AllArgsConstructor  // ID값에는 필요없으므로 삭제해야함 -> strategy = GenerationType.IDENTITY 이부분때문에 저장시 오류
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

    private boolean isDeleted = false;

    public RoomMemberShip(Member member, ChatRoom chatRoom) {
        super();
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    //소프트 딜리트
    public void Deleted(){
        this.isDeleted = true;
    }
}