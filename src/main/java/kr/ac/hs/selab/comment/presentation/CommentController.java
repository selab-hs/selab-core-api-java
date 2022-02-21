package kr.ac.hs.selab.comment.presentation;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostResponse;
import kr.ac.hs.selab.comment.dto.response.CommentFindResponse;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.comment.facade.CommentFacade;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
@RestController
public class CommentController implements CommentSdk {
    private final CommentService commentService;
    private final CommentFacade commentFacade;

    @Override
    @PostMapping("/posts/{postId}/comments")
    public ResponseTemplate<CommentResponse> create(@PathVariable Long postId,
                                                    @Valid @RequestBody CommentRequest request) {
        String memberEmail = SecurityUtils.getCurrentUsername();
        CommentCreateDto dto = CommentConverter.toCommentCreateDto(request, postId, memberEmail);

        CommentResponse response = commentFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.COMMENT_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/comments/{commentId}")
    public ResponseTemplate<CommentFindResponse> find(@PathVariable Long commentId) {
        CommentFindResponse response = commentService.findCommentResponseById(commentId);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping("/posts/{postId}/comments")
    public ResponseTemplate<CommentFindByPostResponse> findByPost(@PathVariable Long postId) {
        CommentFindByPostResponse response = commentFacade.findCommentsResponseByPostId(postId);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/comments/{commentId}")
    public ResponseTemplate<CommentResponse> update(@PathVariable Long commentId,
                                                    @Valid @RequestBody CommentRequest request) {
        CommentUpdateDto dto = CommentConverter.toCommentUpdateDto(commentId, request);

        CommentResponse response = commentService.update(dto);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/comments/{commentId}")
    public ResponseTemplate<CommentResponse> delete(@PathVariable Long commentId) {
        CommentResponse response = commentFacade.delete(commentId);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_DELETE_SUCCESS, response);
    }
}
