package KYJC.chatApplication.repository;

import KYJC.chatApplication.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
