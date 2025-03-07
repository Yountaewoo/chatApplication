package KYJC.chatApplication.roomMemberShip;

import KYJC.chatApplication.entity.Member;
import KYJC.chatApplication.repository.MemberRepository;
import KYJC.chatApplication.entity.ChatRoom;
import KYJC.chatApplication.repository.ChatRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomMemberShipServiceTest {

    @Mock
    private RoomMemberShipRepository roomMemberShipRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @InjectMocks
    private RoomMemberShipService roomMemberShipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        roomMemberShipService.findAll();
        verify(roomMemberShipRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(roomMemberShipRepository.findById(id)).thenReturn(Optional.of(new RoomMemberShip()));
        Optional<RoomMemberShip> result = roomMemberShipService.findById(id);
        assertTrue(result.isPresent());
        verify(roomMemberShipRepository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        RoomMemberShip roomMemberShip = new RoomMemberShip();
        when(roomMemberShipRepository.save(roomMemberShip)).thenReturn(roomMemberShip);
        RoomMemberShip result = roomMemberShipService.save(roomMemberShip);
        assertNotNull(result);
        verify(roomMemberShipRepository, times(1)).save(roomMemberShip);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        roomMemberShipService.deleteById(id);
        verify(roomMemberShipRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindMemberById() {
        Long memberId = 1L;
        Member member = mock(Member.class);
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        Member result = roomMemberShipService.findMemberById(memberId);
        assertNotNull(result);
        verify(memberRepository, times(1)).findById(memberId);
    }

    @Test
    void testFindChatRoomById() {
        Long chatRoomId = 1L;
        ChatRoom chatRoom = mock(ChatRoom.class);
        when(chatRoomRepository.findById(chatRoomId)).thenReturn(Optional.of(chatRoom));
        ChatRoom result = roomMemberShipService.findChatRoomById(chatRoomId);
        assertNotNull(result);
        verify(chatRoomRepository, times(1)).findById(chatRoomId);
    }
}