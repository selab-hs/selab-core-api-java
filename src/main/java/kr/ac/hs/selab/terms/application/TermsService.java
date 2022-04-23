package kr.ac.hs.selab.terms.application;

import kr.ac.hs.selab.terms.domain.Terms;
import kr.ac.hs.selab.terms.domain.vo.Category;
import kr.ac.hs.selab.terms.infrastructure.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TermsService {
    private final TermsRepository termsRepository;

    @Transactional
    public void sign(Long memberId) {
        save(Category.PRIVACY, memberId);
        save(Category.SERVICE, memberId);
    }

    @Transactional
    public void save(Category category, Long memberId) {
        termsRepository.save(new Terms(category, memberId));
    }
}