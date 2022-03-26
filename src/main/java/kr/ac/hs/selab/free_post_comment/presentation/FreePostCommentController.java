package kr.ac.hs.selab.free_post_comment.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.free_post_comment.dto.request.FreePostCommentRequest;
import kr.ac.hs.selab.free_post_comment.dto.response.FreePostCommentFindByFreePostIdAndPageResponse;
import kr.ac.hs.selab.free_post_comment.dto.response.FreePostCommentFindResponse;
import kr.ac.hs.selab.free_post_comment.dto.response.FreePostCommentResponse;
import kr.ac.hs.selab.free_post_comment.facade.FreePostCommentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
@RestController
public class FreePostCommentController implements FreePostCommentSdk {
    private final FreePostCommentFacade freePostCommentFacade;

    @Override
    @PostMapping("/free-posts/{freePostId}/free-post-comments")
    public ResponseTemplate<FreePostCommentResponse> create(@PathVariable Long freePostId,
                                                            @Validated @RequestBody FreePostCommentRequest request) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var response = freePostCommentFacade.create(freePostId, memberEmail, request);
        return ResponseTemplate.created(ResponseMessage.FREE_POST_COMMENT_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/free-post-comments/{commentId}")
    public ResponseTemplate<FreePostCommentFindResponse> findByFreePostCommentId(@PathVariable Long commentId) {
        var response = freePostCommentFacade.findByFreePostCommentId(commentId);
        return ResponseTemplate.ok(ResponseMessage.FREE_POST_COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping("/free-posts/{freePostId}/free-post-comments")
    public ResponseTemplate<FreePostCommentFindByFreePostIdAndPageResponse> findByFreePostIdAndPage(@PathVariable Long freePostId,
                                                                                                    @PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var response = freePostCommentFacade.findByFreePostIdAndPage(freePostId, pageable);
        return ResponseTemplate.ok(ResponseMessage.FREE_POST_COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/free-post-comments/{commentId}")
    public ResponseTemplate<FreePostCommentResponse> update(@PathVariable Long commentId,
                                                            @Validated @RequestBody FreePostCommentRequest request) {
        var response = freePostCommentFacade.update(commentId, request);
        return ResponseTemplate.ok(ResponseMessage.FREE_POST_COMMENT_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/free-post-comments/{commentId}")
    public ResponseTemplate<FreePostCommentResponse> delete(@PathVariable Long commentId) {
        var response = freePostCommentFacade.delete(commentId);
        return ResponseTemplate.ok(ResponseMessage.FREE_POST_COMMENT_DELETE_SUCCESS, response);
    }
}
