package kr.ac.hs.selab.coreQa.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.coreQa.dto.bundle.CoreQaCreateBundle;
import kr.ac.hs.selab.coreQa.dto.request.CoreQaCreateRequest;
import kr.ac.hs.selab.coreQa.dto.response.CoreQaCreateResponse;
import kr.ac.hs.selab.coreQa.facade.CoreQaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/core-qas")
@RequiredArgsConstructor
public class CoreQaController implements CoreQaSdk {
    private final CoreQaFacade coreQaFacade;

    @Override
    @PostMapping
    public ResponseTemplate<CoreQaCreateResponse> create(@Validated CoreQaCreateRequest request) {
        var memberEmail = SecurityUtils.getCurrentUsername();

        var bundle = new CoreQaCreateBundle(
                memberEmail,
                request.getTitle(),
                request.getContent()
        );

        var response = coreQaFacade.save(bundle);

        return ResponseTemplate.ok(
                ResponseMessage.CORE_QA_CREATE_SUCCESS,
                response
        );
    }
}