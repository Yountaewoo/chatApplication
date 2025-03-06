package KYJC.chatApplication.request;

public record MessageRequest(
        Long reciverId,
        String content
) {
}
