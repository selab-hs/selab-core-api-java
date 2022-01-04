package kr.ac.hs.selab.member.infrastructure;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Nickname;
import kr.ac.hs.selab.member.domain.vo.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Override
    <S extends Member> S save(S entity);

    boolean existsByEmail(Email email);

    boolean existsByStudentId(StudentId studentId);

    boolean existsByNickname(Nickname nickname);
}