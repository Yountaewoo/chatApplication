package KYJC.chatApplication.response;

public record MessageDetailResponse(
        String content,
        Long receiverId
) {
}
