package KYJC.chatApplication.roomMemberShip;

public class RoomMemberShipResponse {
    private Long id;
    private Long userId;
    private Long chatRoomId;

    public RoomMemberShipResponse(Long id, Long userId, Long chatRoomId) {
        this.id = id;
        this.userId = userId;
        this.chatRoomId = chatRoomId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }
}