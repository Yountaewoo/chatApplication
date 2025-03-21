package KYJC.chatApplication.restController;

import KYJC.chatApplication.AccessToken;
import KYJC.chatApplication.LoginMember;
import KYJC.chatApplication.request.CreateMemberRequest;
import KYJC.chatApplication.request.DeleteMemberRequest;
import KYJC.chatApplication.request.LoginRequest;
import KYJC.chatApplication.response.MemberSignupResponse;
import KYJC.chatApplication.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원가입
    @PostMapping("/signUp")
    public MemberSignupResponse create(@RequestBody CreateMemberRequest request) {
        return memberService.create(request);
    }

    //로그인
    @PostMapping("/signIn")
    public AccessToken login(@RequestBody LoginRequest request) {
        return memberService.login(request);
    }

    //탈퇴
    @DeleteMapping("/member")
    public void delete(@LoginMember String loginId,
                       @RequestBody DeleteMemberRequest deleteMemberRequest) {
        memberService.delete(loginId, deleteMemberRequest);
    }
}
