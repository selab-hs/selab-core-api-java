package kr.ac.hs.selab.notice_comment.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.notice_comment.dto.request.NoticeCommentRequest;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentFindByNoticeIdAndPageResponse;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentFindResponse;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentResponse;
import kr.ac.hs.selab.notice_comment.facade.NoticeCommentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
@RestController
public class NoticeCommentController implements NoticeCommentSdk {
    private final NoticeCommentFacade noticeCommentFacade;

    @Override
    @PostMapping("/notices/{noticeId}/notice-comments")
    public ResponseTemplate<NoticeCommentResponse> create(@PathVariable Long noticeId,
                                                          @Validated @RequestBody NoticeCommentRequest request) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var response = noticeCommentFacade.create(noticeId, memberEmail, request);

        return ResponseTemplate.created(ResponseMessage.NOTICE_COMMENT_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/notice-comments/{commentId}")
    public ResponseTemplate<NoticeCommentFindResponse> find(@PathVariable Long commentId) {
        var response = noticeCommentFacade.findByNoticeCommentId(commentId);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping("/notices/{noticeId}/notice-comments")
    public ResponseTemplate<NoticeCommentFindByNoticeIdAndPageResponse> findByNoticeIdAndPage(@PathVariable Long noticeId,
                                                                                              @PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var response = noticeCommentFacade.findByNoticeIdAndPage(noticeId, pageable);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/notice-comments/{commentId}")
    public ResponseTemplate<NoticeCommentResponse> update(@PathVariable Long commentId,
                                                          @Validated @RequestBody NoticeCommentRequest request) {
        var response = noticeCommentFacade.update(commentId, request);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_COMMENT_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/notice-comments/{noticeCommentId}")
    public ResponseTemplate<NoticeCommentResponse> delete(@PathVariable Long noticeCommentId) {
        var response = noticeCommentFacade.delete(noticeCommentId);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_COMMENT_DELETE_SUCCESS, response);
    }
}
