package kr.ac.hs.selab.terms.infrastructure;

import kr.ac.hs.selab.terms.domain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepository extends JpaRepository<Terms, Long> {
}