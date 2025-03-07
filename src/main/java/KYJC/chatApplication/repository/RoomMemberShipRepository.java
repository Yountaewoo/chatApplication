package KYJC.chatApplication.repository;

import KYJC.chatApplication.entity.RoomMemberShip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMemberShipRepository extends JpaRepository<RoomMemberShip, Long> {
}