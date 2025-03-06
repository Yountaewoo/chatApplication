package KYJC.chatApplication.roomMemberShip;

import lombok.Getter;

@Getter
public class RoomMemberShipResponse {
    private final Long id;
    private final Long userId;
    private final Long chatRoomId;

    public RoomMemberShipResponse(Long id, Long userId, Long chatRoomId) {
        this.id = id;
        this.userId = userId;
        this.chatRoomId = chatRoomId;
    }

}