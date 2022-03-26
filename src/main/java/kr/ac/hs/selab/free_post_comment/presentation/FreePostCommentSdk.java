package kr.ac.hs.selab.free_post_comment.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.free_post_comment.dto.request.FreePostCommentRequest;
import kr.ac.hs.selab.free_post_comment.dto.response.FreePostCommentFindByFreePostIdAndPageResponse;
import kr.ac.hs.selab.free_post_comment.dto.response.FreePostCommentFindResponse;
import kr.ac.hs.selab.free_post_comment.dto.response.FreePostCommentResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "Free Post Comment REST API", description = "자유게시글 댓글 api")
public interface FreePostCommentSdk {
    @Operation(summary = "자유게시글 댓글 생성", description = "자유게시글 댓글 정보를 이용해서 댓글을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 생성 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 생성 실패")
    })
    ResponseTemplate<FreePostCommentResponse> create(@Parameter(description = "자유게시글 id 값") Long freePostId,
                                                     @Parameter(description = "자유게시글 댓글 정보") FreePostCommentRequest request);

    @Operation(summary = "자유게시글 댓글 조회", description = "자유게시글 댓글 정보를 이용해서 댓글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 조회 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 조회 실패")
    })
    ResponseTemplate<FreePostCommentFindResponse> findByFreePostCommentId(@Parameter(description = "자유게시글 댓글 id 값") Long commentId);

    @Operation(summary = "자유게시글의 전체 댓글 조회", description = "자유게시글에 해당하는 전체 댓글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 조회 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 조회 실패")
    })
    ResponseTemplate<FreePostCommentFindByFreePostIdAndPageResponse> findByFreePostIdAndPage(@Parameter(description = "자유게시글 id 값") Long freePostId,
                                                                                             @Parameter(description = "페이지 정보") Pageable pageable);

    @Operation(summary = "자유게시글 수정", description = "변경할 자유게시글 댓글 정보를 이용해서 댓글을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 수정 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 수정 실패")
    })
    ResponseTemplate<FreePostCommentResponse> update(@Parameter(description = "자유게시글 댓글 id 값") Long commentId,
                                                   @Parameter(description = "자유게시글 댓글 정보") FreePostCommentRequest request);

    @Operation(summary = "자유게시글 삭제", description = "자유게시글 댓글을 지정하여 댓글을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 삭제 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 삭제 실패")
    })
    ResponseTemplate<FreePostCommentResponse> delete(@Parameter(description = "자유게시글 댓글 id 값") Long commentId);
}
