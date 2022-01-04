package kr.ac.hs.selab.member.infrastructure;

import kr.ac.hs.selab.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Override
    <S extends Member> S save(S entity);
}