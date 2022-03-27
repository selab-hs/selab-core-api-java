package kr.ac.hs.selab.coreQa.application;

import kr.ac.hs.selab.coreQa.domain.CoreQa;
import kr.ac.hs.selab.coreQa.dto.response.CoreQaCreateResponse;
import kr.ac.hs.selab.coreQa.infrastructure.CoreQaRepository;
import lombok.RequiredArgsConstructor;
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
}