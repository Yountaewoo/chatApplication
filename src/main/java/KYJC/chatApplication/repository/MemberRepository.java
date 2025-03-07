package KYJC.chatApplication.repository;

import KYJC.chatApplication.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByloginId(String s);


//    Optional<Member> findByloginId(String loginId);
}
