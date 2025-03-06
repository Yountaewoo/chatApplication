package KYJC.chatApplication.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByloginId(String s);

    Optional<Object> findByuserId(String userId);
}
