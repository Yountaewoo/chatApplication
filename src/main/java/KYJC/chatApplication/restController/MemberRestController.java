package KYJC.chatApplication.restController;

import KYJC.chatApplication.AccessToken;
import KYJC.chatApplication.JwtProvider;
import KYJC.chatApplication.LoginMember;
import KYJC.chatApplication.request.CreateMemberRequest;
import KYJC.chatApplication.request.LoginRequest;
import KYJC.chatApplication.response.MemberSignupResponse;
import KYJC.chatApplication.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberRestController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    public MemberRestController(MemberService memberService, JwtProvider jwtProvider) {
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;
    }

    //회원가입
    @PostMapping("/signUp")
    public MemberSignupResponse create(@RequestBody CreateMemberRequest request){
        return memberService.create(request);
    }

    //로그인
    @PostMapping("/signIn")
    public AccessToken login(@RequestBody LoginRequest request){
        return memberService.login(request);
    }

    //탈퇴
    @DeleteMapping("/member/{memberId}")
    public void delete(@LoginMember String loginId,
                       @PathVariable Long memberId){
        memberService.delete(loginId,memberId);
    }
}
