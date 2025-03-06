package KYJC.chatApplication.roomMemberShip;

    import jakarta.persistence.*;
    import KYJC.chatApplication.entity.BaseEntity;
    import lombok.AllArgsConstructor;
    import lombok.NoArgsConstructor;

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
            this.member = member;
            this.chatRoom = chatRoom;
        }

        public Long getId() {
            return id;
        }

        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
            this.member = member;
        }

        public ChatRoom getChatRoom() {
            return chatRoom;
        }

        public void setChatRoom(ChatRoom chatRoom) {
            this.chatRoom = chatRoom;
        }
    }