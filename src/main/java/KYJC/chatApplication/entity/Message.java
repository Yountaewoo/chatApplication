package KYJC.chatApplication.entity;

import KYJC.chatApplication.Member.Member;
import jakarta.persistence.*;

@Entity
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member receiver;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member sender;

    @Column(nullable = false)
    private String content;

    private boolean idDeleted = false;

    public Message(Member receiver, Member sender, String content) {
        this.receiver = receiver;
        this.sender = sender;
        this.content = content;
    }

    protected Message() {
    }

    public Long getId() {
        return id;
    }

    public Member getReceiver() {
        return receiver;
    }

    public Member getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public boolean isIdDeleted() {
        return idDeleted;
    }
}
