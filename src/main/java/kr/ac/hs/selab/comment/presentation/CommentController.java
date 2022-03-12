package kr.ac.hs.selab.comment.presentation;

import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.dto.CommentFindByPostIdAndPageDto;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostIdAndPageResponse;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
@RestController
public class CommentController implements CommentSdk {
    private final CommentFacade commentFacade;

    @Override
    @PostMapping("/posts/{postId}/comments")
    public ResponseTemplate<CommentResponse> create(@PathVariable Long postId,
                                                    @Validated @RequestBody CommentRequest request) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var dto = CommentConverter.toCommentCreateDto(request, postId, memberEmail);
        var response = commentFacade.create(dto);

        return ResponseTemplate.created(ResponseMessage.COMMENT_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/comments/{commentId}")
    public ResponseTemplate<CommentFindResponse> find(@PathVariable Long commentId) {
        var response = commentFacade.findCommentResponseById(commentId);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping("/posts/{postId}/comments")
    public ResponseTemplate<CommentFindByPostIdAndPageResponse> findByPostAndPage(@PathVariable Long postId,
                                                                                  @PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var dto = new CommentFindByPostIdAndPageDto(postId, pageable);
        var response = commentFacade.findCommentsResponseByPostId(dto);

        return ResponseTemplate.ok(ResponseMessage.COMMENT_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/comments/{commentId}")
    public ResponseTemplate<CommentResponse> update(@PathVariable Long commentId,
                                                    @Validated @RequestBody CommentRequest request) {
        var dto = CommentConverter.toCommentUpdateDto(commentId, request);
        var response = commentFacade.update(dto);

        return ResponseTemplate.ok(ResponseMessage.COMMENT_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/comments/{commentId}")
    public ResponseTemplate<CommentResponse> delete(@PathVariable Long commentId) {
        var response = commentFacade.delete(commentId);
        return ResponseTemplate.ok(ResponseMessage.COMMENT_DELETE_SUCCESS, response);
    }
}
