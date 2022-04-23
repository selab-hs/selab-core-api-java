package kr.ac.hs.selab.core_qa.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.core_qa.dto.request.CoreQaCreateRequest;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaFindByIdResponse;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaFindByPageResponse;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaResponse;
import kr.ac.hs.selab.core_qa.facade.CoreQaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/core-qas")
@RequiredArgsConstructor
public class CoreQaController implements CoreQaSdk {
    private final CoreQaFacade coreQaFacade;

    @Override
    @PostMapping
    public ResponseTemplate<CoreQaResponse> create(@Validated @RequestBody CoreQaCreateRequest request) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var response = coreQaFacade.create(memberEmail, request);

        return ResponseTemplate.ok(ResponseMessage.CORE_QA_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseTemplate<CoreQaFindByIdResponse> find(@PathVariable Long id) {
        var response = coreQaFacade.findById(id);
        return ResponseTemplate.ok(ResponseMessage.CORE_QA_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping
    public ResponseTemplate<CoreQaFindByPageResponse> findByPage(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var response = coreQaFacade.findByPage(pageable);
        return ResponseTemplate.ok(ResponseMessage.CORE_QA_FIND_SUCCESS, response);
    }
}