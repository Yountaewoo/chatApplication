package KYJC.chatApplication.roomMemberShip;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomMemberShipRequest {
    private Long userId;
    private Long chatRoomId;

}