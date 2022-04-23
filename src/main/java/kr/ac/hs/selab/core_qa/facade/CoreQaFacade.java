package kr.ac.hs.selab.core_qa.facade;

import kr.ac.hs.selab.core_qa.application.CoreQaService;
import kr.ac.hs.selab.core_qa.converter.CoreQaConverter;
import kr.ac.hs.selab.core_qa.dto.bundle.CoreQaCreateBundle;
import kr.ac.hs.selab.core_qa.dto.bundle.CoreQaFindByPageBundle;
import kr.ac.hs.selab.core_qa.dto.request.CoreQaCreateRequest;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaFindByIdResponse;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaFindByPageResponse;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaResponse;
import kr.ac.hs.selab.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class CoreQaFacade {
    private final MemberService memberService;
    private final CoreQaService coreQaService;

    @Transactional
    public CoreQaResponse create(String memberEmail, CoreQaCreateRequest request) {
        var memberId = memberService.findByEmail(memberEmail).getId();
        var coreQaRequest = new CoreQaCreateBundle(memberId, request.getTitle(), request.getContent());

        var qa = coreQaService.create(coreQaRequest);
        return new CoreQaResponse(qa.getId());
    }

    public CoreQaFindByIdResponse findById(Long id) {
        var qa = coreQaService.findById(id);
        return CoreQaConverter.toCoreQaFindByIdResponse(qa);
    }

    public CoreQaFindByPageResponse findByPage(Pageable pageable) {
        var bundle = CoreQaFindByPageBundle.builder()
                .totalCount(coreQaService.count())
                .pageable(pageable)
                .coreQas(coreQaService.findByPage(pageable))
                .build();
        return CoreQaConverter.toCoreQaFindByPageResponse(bundle);
    }
}