package kr.ac.hs.selab.notice.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.notice.converter.NoticeConverter;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindAllByPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeFindResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeResponse;
import kr.ac.hs.selab.notice.facade.NoticeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/notices")
@RestController
public class NoticeController implements NoticeSdk {
    private final NoticeFacade noticeFacade;

    @Override
    @PostMapping
    public ResponseTemplate<NoticeResponse> create(@Validated @RequestBody NoticeRequest request) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var response = noticeFacade.create(memberEmail, request);
        return ResponseTemplate.created(ResponseMessage.NOTICE_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseTemplate<NoticeFindResponse> find(@PathVariable Long id) {
        var response = noticeFacade.findNoticeResponseById(id);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_FIND_SUCCESS, response);
    }

    @GetMapping
    public ResponseTemplate<NoticeFindAllByPageResponse> findAllByPage(@PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var response = noticeFacade.findNoticeFindAllByPageResponse(pageable);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseTemplate<NoticeResponse> update(@PathVariable Long id,
                                                   @Validated @RequestBody NoticeRequest request) {
        var response = noticeFacade.update(id, request);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseTemplate<NoticeResponse> delete(@PathVariable Long id) {
        var response = noticeFacade.delete(id);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_DELETE_SUCCESS, response);
    }
}