package kr.ac.hs.selab.commentLike.presentation;

import kr.ac.hs.selab.commentLike.application.CommentLikeService;
import kr.ac.hs.selab.commentLike.dto.CommentLikeDto;
import kr.ac.hs.selab.commentLike.dto.CommentLikeFIndDto;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeFindResponse;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeResponse;
import kr.ac.hs.selab.commentLike.facade.CommentLikeFacade;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
@RestController
public class CommentLikeController implements CommentLikeSdk {
    private final CommentLikeService commentLikeService;
    private final CommentLikeFacade commentLikeFacade;

    @Override
    @PostMapping("/{commentId}/likes")
    public ResponseTemplate<CommentLikeResponse> create(@PathVariable Long commentId) {
        String memberEmail = SecurityUtils.getCurrentUsername();
        CommentLikeDto dto = new CommentLikeDto(memberEmail, commentId);

        CommentLikeResponse response = commentLikeFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.COMMENT_LIKE_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{commentId}/likes")
    public ResponseTemplate<CommentLikeFindResponse> find(@PathVariable Long commentId) {
        CommentLikeFIndDto dto = new CommentLikeFIndDto(commentId);
        CommentLikeFindResponse response = commentLikeFacade.find(dto);

        return ResponseTemplate.ok(ResponseMessage.COMMENT_LIKE_FIND_SUCCESS, response);
    }

    @Override
    @DeleteMapping("/likes/{id}")
    public ResponseTemplate<CommentLikeResponse> delete(@PathVariable Long id) {
        CommentLikeResponse response = commentLikeService.delete(id);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_LIKE_DELETE_SUCCESS, response);
    }
}
