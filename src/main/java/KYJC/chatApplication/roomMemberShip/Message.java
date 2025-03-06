//package KYJC.chatApplication.roomMemberShip;
//
//import jakarta.persistence.*;
//
//import java.lang.reflect.Member;
//import java.time.LocalDateTime;
//
//@Entity
//public class Message {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id", nullable = false)
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "chat_room_id", nullable = false)
//    private ChatRoom chatRoom;
//
//    @Column(nullable = false)
//    private String content;
//
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//
//    protected Message() {}
//
//    public Message(Member member, ChatRoom chatRoom, String content, LocalDateTime timestamp) {
//        this.member = member;
//        this.chatRoom = chatRoom;
//        this.content = content;
//        this.timestamp = timestamp;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }
//
//    public ChatRoom getChatRoom() {
//        return chatRoom;
//    }
//
//    public void setChatRoom(ChatRoom chatRoom) {
//        this.chatRoom = chatRoom;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//}