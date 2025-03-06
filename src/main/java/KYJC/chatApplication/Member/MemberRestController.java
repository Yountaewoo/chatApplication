package KYJC.chatApplication.Member;

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
    @DeleteMapping("/member")
    public void delete(@LoginMember String loginId,
                       @RequestParam Long id){
        memberService.delete(loginId,id);
    }


}
