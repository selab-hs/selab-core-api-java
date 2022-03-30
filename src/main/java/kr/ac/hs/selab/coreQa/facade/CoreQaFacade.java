package kr.ac.hs.selab.coreQa.facade;

import kr.ac.hs.selab.coreQa.application.CoreQaService;
import kr.ac.hs.selab.coreQa.dto.bundle.CoreQaCreateBundle;
import kr.ac.hs.selab.coreQa.dto.response.CoreQaCreateResponse;
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