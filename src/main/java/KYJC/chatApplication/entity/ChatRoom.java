package KYJC.chatApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isDeleted = false;

    public ChatRoom() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    // 정적 팩토리 메서드: 새로운 ChatRoom 생성
    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.name = name;
        chatRoom.isDeleted = false;
        return chatRoom;
    }

    // 비즈니스 로직: 채팅방 이름 변경
    public void updateName(String newName) {
        this.name = newName;
    }
}
