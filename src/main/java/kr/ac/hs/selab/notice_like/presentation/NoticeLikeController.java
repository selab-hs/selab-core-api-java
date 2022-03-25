package kr.ac.hs.selab.notice_like.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.notice_like.dto.response.NoticeLikeFindResponse;
import kr.ac.hs.selab.notice_like.dto.response.NoticeLikeResponse;
import kr.ac.hs.selab.notice_like.facade.NoticeLikeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/notices")
@RestController
public class NoticeLikeController implements NoticeLikeSdk {
    private final NoticeLikeFacade noticeLikeFacade;

    @Override
    @PostMapping("/{noticeId}/likes")
    public ResponseTemplate<NoticeLikeResponse> create(@PathVariable Long noticeId) {
        var memberEmail = SecurityUtils.getCurrentUsername();

        var response = noticeLikeFacade.create(memberEmail, noticeId);
        return ResponseTemplate.created(ResponseMessage.NOTICE_LIKE_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{noticeId}/likes")
    public ResponseTemplate<NoticeLikeFindResponse> find(@PathVariable Long noticeId) {
        var response = noticeLikeFacade.find(noticeId);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_LIKE_FIND_SUCCESS, response);
    }

    @Override
    @DeleteMapping("/likes/{noticeId}")
    public ResponseTemplate<NoticeLikeResponse> delete(@PathVariable Long noticeId) {
        var response = noticeLikeFacade.delete(noticeId);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_LIKE_DELETE_SUCCESS, response);
    }
}
