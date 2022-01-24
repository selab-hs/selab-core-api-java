package kr.ac.hs.selab.member.infrastructure;

import java.util.Optional;
import kr.ac.hs.selab.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    boolean existsByStudentId(String studentId);

    boolean existsByNickname(String nickname);

    Optional<Member> findByEmail(String email);
}