package kr.ac.hs.selab.comment.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostIdAndPageResponse;
import kr.ac.hs.selab.comment.dto.response.CommentFindResponse;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import org.springframework.data.domain.Pageable;

@Api(tags = "Comment REST API", description = "댓글 api")
public interface CommentSdk {
    @Operation(summary = "댓글 생성", description = "게시글 정보를 이용해서 댓글을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "댓글 생성 성공"),
            @ApiResponse(code = 400, message = "댓글 생성 실패")
    })
    ResponseTemplate<CommentResponse> create(@Parameter(description = "게시글 id 값") Long postId,
                                             @Parameter(description = "댓글 정보") CommentRequest request);

    @Operation(summary = "댓글 조회", description = "댓글 정보를 이용해서 댓글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "댓글 조회 성공"),
            @ApiResponse(code = 400, message = "댓글 조회 실패")
    })
    ResponseTemplate<CommentFindResponse> find(@Parameter(description = "댓글 id 값") Long postId);

    @Operation(summary = "게시글의 전체 댓글 조회", description = "게시글에 해당하는 전체 댓글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "댓글 조회 성공"),
            @ApiResponse(code = 400, message = "댓글 조회 실패")
    })
    ResponseTemplate<CommentFindByPostIdAndPageResponse> findByPostAndPage(Long postId,
                                                                           Pageable pageable);

    @Operation(summary = "댓글 수정", description = "새로운 댓글 정보를 이용해서 댓글을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "댓글 수정 성공"),
            @ApiResponse(code = 400, message = "댓글 수정 실패")
    })
    ResponseTemplate<CommentResponse> update(@Parameter(description = "댓글 id 값") Long commentId,
                                             @Parameter(description = "댓글 정보") CommentRequest request);

    @Operation(summary = "댓글 삭제", description = "댓글을 지정하여 댓글을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "댓글 삭제 성공"),
            @ApiResponse(code = 400, message = "댓글 삭제 실패")
    })
    ResponseTemplate<CommentResponse> delete(@Parameter(description = "댓글 id 값") Long commentId);
}
