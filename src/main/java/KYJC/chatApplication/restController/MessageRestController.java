package KYJC.chatApplication.restController;

import KYJC.chatApplication.Member.Member;
import KYJC.chatApplication.request.MessageRequest;
import KYJC.chatApplication.response.MessageResponse;
import KYJC.chatApplication.service.MessageService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;

@RestController
public class MessageRestController {

    private final MessageService messageService;

    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public MessageResponse create(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                  @RequestBody MessageRequest messageRequest) {
        Member member = loginMemberResolver.resolveUserFromToken(authorization);
        return messageService.create(member, messageRequest);
    }

    @GetMapping("/messages/{roomId}")
    public
}
