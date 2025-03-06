//package KYJC.chatApplication.roomMemberShip;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class RoomMemberShipControllerTest {
//
//    @Mock
//    private RoomMemberShipService service;
//
//    @InjectMocks
//    private RoomMemberShipController controller;
//
//    private MockMvc mockMvc;
//    private RoomMemberShip roomMemberShip;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//        roomMemberShip = new RoomMemberShip(new Member(), new ChatRoom());
//    }
//
//    @Test
//    void testGetAllRoomMemberShips() throws Exception {
//        when(service.findAll()).thenReturn(Arrays.asList(roomMemberShip));
//        mockMvc.perform(get("/api/room-memberships"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").exists());
//    }
//
//    @Test
//    void testGetRoomMemberShipById() throws Exception {
//        when(service.findById(1L)).thenReturn(Optional.of(roomMemberShip));
//        mockMvc.perform(get("/api/room-memberships/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").exists());
//    }
//
//    @Test
//    void testCreateRoomMemberShip() throws Exception {
//        when(service.save(any(RoomMemberShip.class))).thenReturn(roomMemberShip);
//        mockMvc.perform(post("/api/room-memberships")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"member\":{}, \"chatRoom\":{}}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").exists());
//    }
//
//    @Test
//    void testDeleteRoomMemberShip() throws Exception {
//        doNothing().when(service).deleteById(1L);
//        mockMvc.perform(delete("/api/room-memberships/1"))
//                .andExpect(status().isNoContent());
//    }
//}