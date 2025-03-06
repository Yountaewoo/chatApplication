package KYJC.chatApplication.service;

import KYJC.chatApplication.entity.Member;
import KYJC.chatApplication.repository.MemberRepository;
import KYJC.chatApplication.entity.Message;
import KYJC.chatApplication.repository.ChatRoomRepository;
import KYJC.chatApplication.repository.MessageRepository;
import KYJC.chatApplication.request.MessageRequest;
import KYJC.chatApplication.response.MessageResponse;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    public MessageService(MessageRepository messageRepository,
                          MemberRepository memberRepository,
                          ChatRoomRepository chatRoomRepository) {
        this.messageRepository = messageRepository;
        this.memberRepository = memberRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public MessageResponse create(Member member, MessageRequest messageRequest) {
        Member sender = memberRepository.findById(member.getId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자가 없습니다."));

        Member receiver = memberRepository.findById(messageRequest.reciverId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자가 없습니다."));
        Message message = new Message(receiver, sender, messageRequest.content());
        messageRepository.save(message);
        return new MessageResponse(message.getId(), message.getContent());
    }

//    public MessageDetailResponse get(Member member, Long roomId) {
//        chatRoomRepository.findById(roomId);
//
//    }
}
