package KYJC.chatApplication.restController;

import KYJC.chatApplication.request.ChatRoomRequest;
import KYJC.chatApplication.request.ChatRoomUpdateRequest;
import KYJC.chatApplication.response.ChatRoomResponse;
import KYJC.chatApplication.service.ChatRoomService;
import KYJC.chatApplication.LoginMember;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChatRoomResponse createChatRoom(
            @RequestBody ChatRoomRequest request,
            @LoginMember String loginId) {
        return chatRoomService.createChatRoom(request.name(),loginId);
    }

    @PutMapping("/{chatRoomId}")
    @ResponseStatus(HttpStatus.OK)
    public ChatRoomResponse updateChatRoom(
            @PathVariable Long chatRoomId,
            @RequestBody ChatRoomUpdateRequest request,
            @LoginMember String loginId) {
        return chatRoomService.updateChatRoom(chatRoomId, request.name());
    }
}
