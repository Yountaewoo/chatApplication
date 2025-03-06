package KYJC.chatApplication.Member;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public MemberService(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    //회원가입 + 생성일 추가
    public MemberSignupResponse create(CreateMemberRequest request) {
        Member member = memberRepository.save(new Member(
                request.username(),
                request.loginId(),
                request.password()
        ));

        return new MemberSignupResponse(
                member.getId(),
                member.getUserName(),
                member.getLoginId(),
                member.getPassword(),
                member.getCreatedAt()
        );
    }

    //로그인
    public AccessToken login(LoginRequest request) {
        //가입된 회원인지 확인
        Member member = memberRepository.findByloginId(request.loginId());

        if (member == null || !member.isCorrectPassword((request.password()))) {
            throw new IllegalArgumentException(" 등록되지 않은 아이디 / 비밀번호 오류");
        } else {
            return new AccessToken(jwtProvider.createToken(member.getLoginId()));
        }
    }

    //회원탈퇴
//    public void delete(String loginId) {
//        Member member = memberRepository.findByloginId(loginId)
//                .orElseThrow(() -> new IllegalArgumentException("회원 정보 찾을 수 없음"));
//
//        memberRepository.deleteById(member.getId());
//    }
    public void delete(String loginId, Long id) {
        // loginId로 회원 조회 (기존 메소드 사용)
        Member member = memberRepository.findByloginId(loginId);
        if (member == null) {
            throw new IllegalArgumentException("회원 정보 찾을 수 없음");
        }

        // ID 검증 후 삭제
        if (member.getId().equals(id)) {
            memberRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("회원 정보와 일치하지 않는 ID입니다.");
        }
    }
}
