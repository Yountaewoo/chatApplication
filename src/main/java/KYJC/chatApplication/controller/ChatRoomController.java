package KYJC.chatApplication.controller;

import KYJC.chatApplication.entity.ChatRoom;
import KYJC.chatApplication.request.ChatRoomRequest;
import KYJC.chatApplication.request.ChatRoomUpdateRequest;
import KYJC.chatApplication.response.ChatRoomResponse;
import KYJC.chatApplication.service.ChatRoomService;
import KYJC.chatApplication.JwtProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final JwtProvider jwtProvider;

    public ChatRoomController(ChatRoomService chatRoomService, JwtProvider jwtProvider) {
        this.chatRoomService = chatRoomService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChatRoomResponse createChatRoom(
            @RequestBody ChatRoomRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {

        String token = extractToken(bearerToken);
        if (!jwtProvider.isValidToken(token)) {
            throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다");
        }

        return chatRoomService.createChatRoom(request.name());
    }

    @PutMapping("/{chatRoomId}")
    @ResponseStatus(HttpStatus.OK)
    public ChatRoomResponse updateChatRoom(
            @PathVariable Long chatRoomId,
            @RequestBody ChatRoomUpdateRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {

        String token = extractToken(bearerToken);
        if (!jwtProvider.isValidToken(token)) {
            throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다");
        }

        return chatRoomService.updateChatRoom(chatRoomId, request.name());
    }

    // Authorization 헤더에서 "Bearer " 접두어를 제거하고 실제 토큰만 추출하는 메서드
    private String extractToken(String bearerToken) {
        final String BEARER_PREFIX = "Bearer ";
        if (bearerToken == null || !bearerToken.startsWith(BEARER_PREFIX)) {
            throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다");
        }
        return bearerToken.substring(BEARER_PREFIX.length());
    }
}
