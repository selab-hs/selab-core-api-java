package kr.ac.hs.selab.core_qa.infrastructure;

import kr.ac.hs.selab.core_qa.domain.CoreQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreQaRepository extends JpaRepository<CoreQa, Long> {
}