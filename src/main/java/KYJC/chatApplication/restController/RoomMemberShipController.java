package KYJC.chatApplication.restController;

import KYJC.chatApplication.entity.Member;
import KYJC.chatApplication.entity.ChatRoom;
import KYJC.chatApplication.entity.RoomMemberShip;
import KYJC.chatApplication.request.RoomMemberShipRequest;
import KYJC.chatApplication.response.RoomMemberShipResponse;
import KYJC.chatApplication.service.RoomMemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room-memberships")
public class RoomMemberShipController {

    private final RoomMemberShipService service;

    @Autowired
    public RoomMemberShipController(RoomMemberShipService service) {
        this.service = service;
    }

    @GetMapping
    public List<RoomMemberShipResponse> getAllRoomMemberShips() {
        return service.findAll().stream()
                .map(roomMemberShip -> new RoomMemberShipResponse(
                        roomMemberShip.getId(),
                        roomMemberShip.getMember().getId(),
                        roomMemberShip.getChatRoom().getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomMemberShipResponse> getRoomMemberShipById(@PathVariable Long id) {
        Optional<RoomMemberShip> roomMemberShip = service.findById(id);
        return roomMemberShip.map(rms -> ResponseEntity.ok(new RoomMemberShipResponse(
                rms.getId(),
                rms.getMember().getId(),
                rms.getChatRoom().getId())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

   @PostMapping
   public RoomMemberShipResponse createRoomMemberShip(@RequestBody RoomMemberShipRequest request) {
       Member member = service.findMemberById(request.getUserId());
       ChatRoom chatRoom = service.findChatRoomById(request.getChatRoomId());
       RoomMemberShip roomMemberShip = new RoomMemberShip(member, chatRoom);
       RoomMemberShip savedRoomMemberShip = service.save(roomMemberShip);
       return new RoomMemberShipResponse(
               savedRoomMemberShip.getId(),
               savedRoomMemberShip.getMember().getId(),
               savedRoomMemberShip.getChatRoom().getId());
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomMemberShip(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}