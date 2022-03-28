package kr.ac.hs.selab.coreQa.application;

import kr.ac.hs.selab.coreQa.domain.CoreQa;
import kr.ac.hs.selab.coreQa.dto.response.CoreQaCreateResponse;
import kr.ac.hs.selab.coreQa.dto.response.CoreQaReadResponse;
import kr.ac.hs.selab.coreQa.infrastructure.CoreQaRepository;
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
}