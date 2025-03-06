package KYJC.chatApplication.entity;

import KYJC.chatApplication.SecurityUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String loginId;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }



    //비밀번호 확인
    public boolean isCorrectPassword(String password){
        return this.getPassword().equals(SecurityUtils.sha256EncryptHex2(password));
    }
}
