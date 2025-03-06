package KYJC.chatApplication.Member;

import java.time.LocalDateTime;

public record MemberSignupResponse(Long Id,
                                   String userName,
                                   String loginId,
                                   String password,
                                   LocalDateTime create) {
}
