package kr.ac.hs.selab.core_qa.application;

import kr.ac.hs.selab.core_qa.domain.CoreQa;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaCreateResponse;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaReadResponse;
import kr.ac.hs.selab.core_qa.infrastructure.CoreQaRepository;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoreQaService {
    private final CoreQaRepository coreQaRepository;

    @Transactional
    public CoreQaCreateResponse save(String title, String content, Long memberId) {

        CoreQa coreQa = coreQaRepository.save(CoreQa.builder()
                .title(title)
                .content(content)
                .memberId(memberId)
                .build());
        return new CoreQaCreateResponse(coreQa.getTitle(), coreQa.getContent());
    }

    @Transactional(readOnly = true)
    public Page<CoreQaReadResponse> getAll(Pageable pageable) {
        return coreQaRepository.findAll(pageable)
                .map(coreQa ->
                        new CoreQaReadResponse(coreQa.getTitle(), coreQa.getContent())

                );
    }

    @Transactional(readOnly = true)
    public CoreQaReadResponse get(Long id) {
        var coreQa = coreQaRepository.findById(id)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.CORE_QA_NOT_EXISTS_ERROR));

        return new CoreQaReadResponse(coreQa.getTitle(), coreQa.getContent());
    }
}