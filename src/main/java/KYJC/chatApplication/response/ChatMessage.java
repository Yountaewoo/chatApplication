package KYJC.chatApplication.response;

import java.time.LocalDateTime;

public record ChatMessage(
        Long roomId,    // 메시지가 속한 채팅방의 id
        Long senderId,  // 메시지를 보낸 Member의 id
        String content,
        LocalDateTime timestamp
) {}
