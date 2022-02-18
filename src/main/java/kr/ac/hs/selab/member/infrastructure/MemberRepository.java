package kr.ac.hs.selab.member.infrastructure;

import java.util.Optional;

import kr.ac.hs.selab.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Transactional(readOnly = true)
    boolean existsByEmail(String email);

    @Transactional(readOnly = true)
    boolean existsByStudentId(String studentId);

    @Transactional(readOnly = true)
    boolean existsByNickname(String nickname);

    @Transactional(readOnly = true)
    Optional<Member> findByEmail(String email);
}