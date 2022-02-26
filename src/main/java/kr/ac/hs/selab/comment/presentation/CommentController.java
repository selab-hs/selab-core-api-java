package kr.ac.hs.selab.comment.presentation;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.dto.CommentFindByPostAndPageDto;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostAndPageResponse;
import kr.ac.hs.selab.comment.dto.response.CommentFindResponse;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.comment.facade.CommentFacade;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
        final var memberEmail = SecurityUtils.getCurrentUsername();
        final var dto = CommentConverter.toCommentCreateDto(request, postId, memberEmail);
        final var response = commentFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.COMMENT_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/comments/{commentId}")
    public ResponseTemplate<CommentFindResponse> find(@PathVariable Long commentId) {
        final var response = commentService.findCommentResponseById(commentId);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping("/posts/{postId}/comments")
    public ResponseTemplate<CommentFindByPostAndPageResponse> findByPostAndPage(@PathVariable Long postId,
                                                                                @PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        final var dto = new CommentFindByPostAndPageDto(postId, pageable);
        final var response = commentFacade.findCommentsResponseByPostId(dto);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/comments/{commentId}")
    public ResponseTemplate<CommentResponse> update(@PathVariable Long commentId,
                                                    @Valid @RequestBody CommentRequest request) {
        final var dto = CommentConverter.toCommentUpdateDto(commentId, request);
        final var response = commentService.update(dto);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/comments/{commentId}")
    public ResponseTemplate<CommentResponse> delete(@PathVariable Long commentId) {
        final var response = commentFacade.delete(commentId);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_DELETE_SUCCESS, response);
    }
}
