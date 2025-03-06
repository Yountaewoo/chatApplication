package KYJC.chatApplication.request;

public record CreateMemberRequest(String username,
                                  String loginId,
                                  String password) {
}
