package KYJC.chatApplication.entity;

import KYJC.chatApplication.SecurityUtils;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Column(nullable = false,unique = true)
    private String loginId;

    @Column(nullable = false,unique = true)
    private String password;

    private LocalDateTime deletedAt;

    protected Member() {
    }

    //비밀번호 해쉬화
    public Member(String userName, String loginId, String password) {
        this.userName = userName;
        this.loginId = loginId;
        this.password = SecurityUtils.sha256EncryptHex2(password);
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }


    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    //비밀번호 확인
    public boolean isCorrectPassword(String password){
        return this.getPassword().equals(SecurityUtils.sha256EncryptHex2(password));
    }
}
