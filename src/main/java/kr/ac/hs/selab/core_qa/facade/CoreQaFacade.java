package kr.ac.hs.selab.core_qa.facade;

import kr.ac.hs.selab.core_qa.application.CoreQaService;
import kr.ac.hs.selab.core_qa.dto.bundle.CoreQaCreateBundle;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaCreateResponse;
import kr.ac.hs.selab.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CoreQaFacade {
    private final MemberService memberService;
    private final CoreQaService coreQaService;

    @Transactional
    public CoreQaCreateResponse save(CoreQaCreateBundle bundle) {
        var memberId = memberService.findByEmail(bundle.email()).getId();

        return coreQaService.save(
                bundle.title(),
                bundle.content(),
                memberId
        );
    }
}