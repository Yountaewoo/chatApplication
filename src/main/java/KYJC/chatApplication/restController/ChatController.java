package KYJC.chatApplication.restController;

import KYJC.chatApplication.response.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    // 동적 경로를 사용하여 특정 채팅방에 메시지 전송
    @MessageMapping("/chat/room/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public ChatMessage handleChatMessage(@DestinationVariable String roomId, ChatMessage chatMessage) {
        // 여기서:
        // 1. roomId와 연결된 채팅방(Member, RoomMemberShip 등)을 검증
        // 2. Message 엔티티에 저장 (DB에 저장하는 로직 추가)
        // 3. 필요한 경우 추가 처리 (예: 타임스탬프 추가, 메시지 포맷팅 등)
        return chatMessage;
    }
}
