package kr.ac.hs.selab.core_qa.application;

import kr.ac.hs.selab.core_qa.converter.CoreQaConverter;
import kr.ac.hs.selab.core_qa.domain.CoreQa;
import kr.ac.hs.selab.core_qa.dto.bundle.CoreQaCreateBundle;
import kr.ac.hs.selab.core_qa.infrastructure.CoreQaRepository;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CoreQaService {
    private final CoreQaRepository coreQaRepository;

    @Transactional
    public CoreQa create(CoreQaCreateBundle bundle) {
        return coreQaRepository.save(CoreQaConverter.toCoreQa(bundle));
    }

    public Long count() {
        return coreQaRepository.count();
    }

    public CoreQa findById(Long id) {
        return coreQaRepository.findById(id)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.CORE_QA_NOT_EXISTS_ERROR));
    }

    public Page<CoreQa> findByPage(Pageable pageable) {
        return coreQaRepository.findAll(pageable);
    }
}