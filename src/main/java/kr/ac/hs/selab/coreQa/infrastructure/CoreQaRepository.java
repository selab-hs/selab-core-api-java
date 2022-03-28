package kr.ac.hs.selab.coreQa.infrastructure;

import kr.ac.hs.selab.coreQa.domain.CoreQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreQaRepository extends JpaRepository<CoreQa, Long> {
}