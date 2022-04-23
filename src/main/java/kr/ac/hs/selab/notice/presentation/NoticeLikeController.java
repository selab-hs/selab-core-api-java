package kr.ac.hs.selab.notice.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.notice.dto.response.NoticeLikeFindResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeLikeResponse;
import kr.ac.hs.selab.notice.facade.NoticeLikeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @DeleteMapping("/likes/{noticeLikeId}")
    public ResponseTemplate<NoticeLikeResponse> delete(@PathVariable Long noticeLikeId) {
        var response = noticeLikeFacade.delete(noticeLikeId);
        return ResponseTemplate.ok(ResponseMessage.NOTICE_LIKE_DELETE_SUCCESS, response);
    }
}
