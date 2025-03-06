package KYJC.chatApplication.restController;

import KYJC.chatApplication.Member.LoginMember;
import KYJC.chatApplication.Member.LoginMemberResolver;
import KYJC.chatApplication.Member.Member;
import KYJC.chatApplication.Member.MemberRepository;
import KYJC.chatApplication.request.MessageRequest;
import KYJC.chatApplication.response.MessageDetailResponse;
import KYJC.chatApplication.response.MessageResponse;
import KYJC.chatApplication.service.MessageService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
public class MessageRestController {

    private final MessageService messageService;
    private final LoginMemberResolver loginMemberResolver;
    private final MemberRepository memberRepository;

    public MessageRestController(MessageService messageService, LoginMemberResolver loginMemberResolver,
                                 MemberRepository memberRepository) {
        this.messageService = messageService;
        this.loginMemberResolver = loginMemberResolver;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/messages")
    public MessageResponse create(@LoginMember String loginId,
                                  @RequestBody MessageRequest messageRequest) {
        Member sender = memberRepository.findByloginId(loginId);
        return messageService.create(sender, messageRequest);
    }

//    @GetMapping("/messages/{roomId}")
//    public MessageDetailResponse get(@LoginMember String loginId,
//                                     @PathVariable Long roomId) {
//        Member member = memberRepository.findByloginId(loginId);
//        return messageService.get(member, roomId);
//    }
}
