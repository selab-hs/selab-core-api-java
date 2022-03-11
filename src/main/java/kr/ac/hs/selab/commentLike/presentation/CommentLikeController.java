package kr.ac.hs.selab.commentLike.presentation;

import kr.ac.hs.selab.commentLike.dto.CommentLikeDto;
import kr.ac.hs.selab.commentLike.dto.CommentLikeFindDto;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeFindResponse;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeResponse;
import kr.ac.hs.selab.commentLike.facade.CommentLikeFacade;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@RestController
public class CommentLikeController implements CommentLikeSdk {
    private final CommentLikeFacade commentLikeFacade;

    @Override
    @PostMapping("/{commentId}/likes")
    public ResponseTemplate<CommentLikeResponse> create(@PathVariable Long commentId) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var dto = new CommentLikeDto(memberEmail, commentId);

        var response = commentLikeFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.COMMENT_LIKE_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{commentId}/likes")
    public ResponseTemplate<CommentLikeFindResponse> find(@PathVariable Long commentId) {
        var dto = new CommentLikeFindDto(commentId);
        var response = commentLikeFacade.find(dto);

        return ResponseTemplate.ok(ResponseMessage.COMMENT_LIKE_FIND_SUCCESS, response);
    }

    @Override
    @DeleteMapping("/likes/{id}")
    public ResponseTemplate<CommentLikeResponse> delete(@PathVariable Long id) {
        var response = commentLikeFacade.delete(id);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_LIKE_DELETE_SUCCESS, response);
    }
}
