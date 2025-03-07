package KYJC.chatApplication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateMemberRequest(
        @Size(min = 1, max = 50) @Pattern(regexp = "^[a-zA-Z가-힣0-9]*$", message = "이름은 알파벳, 한글, 숫자만 가능합니다.")
        String username,

        @NotBlank @Size(min = 6, max = 20)
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영어와 숫자만 가능합니다. /6자리 이상")
        String loginId,

        @NotBlank @Size(min = 8, max = 20)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "소문자, 대문자, 숫자, 특수문자 필수 / 8자리 이상")
        String password) {
}
