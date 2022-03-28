package kr.ac.hs.selab.coreQa.presentation;

import kr.ac.hs.selab.common.template.PageResponseTemplate;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.coreQa.application.CoreQaService;
import kr.ac.hs.selab.coreQa.dto.bundle.CoreQaCreateBundle;
import kr.ac.hs.selab.coreQa.dto.request.CoreQaCreateRequest;
import kr.ac.hs.selab.coreQa.dto.response.CoreQaCreateResponse;
import kr.ac.hs.selab.coreQa.dto.response.CoreQaReadResponse;
import kr.ac.hs.selab.coreQa.facade.CoreQaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/core-qas")
@RequiredArgsConstructor
public class CoreQaController implements CoreQaSdk {
    private final CoreQaFacade coreQaFacade;
    private final CoreQaService coreQaService;

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

    @Override
    @GetMapping
    public PageResponseTemplate<CoreQaReadResponse> getCoreQaAll(
            @PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = coreQaService.getAll(pageable);

        return PageResponseTemplate.ok(
                ResponseMessage.CORE_QA_CREATE_SUCCESS,
                response
        );
    }

    @GetMapping("/{id}")
    public ResponseTemplate<CoreQaReadResponse> getCoreQa(@PathVariable Long id) {
        var response = coreQaService.get(id);
        return ResponseTemplate.ok(ResponseMessage.CORE_QA_READ_SUCCESS, response);
    }
}