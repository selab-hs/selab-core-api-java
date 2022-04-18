package kr.ac.hs.selab.free_post.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.SwaggerNote;
import kr.ac.hs.selab.free_post.dto.request.FreePostCommentRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostCommentFindByFreePostIdAndPageResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostCommentFindResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostCommentResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "자유 게시글 댓글 관리")
public interface FreePostCommentSdk {
    @ApiOperation(value = "자유게시글 댓글 생성", notes = SwaggerNote.FREE_POST_COMMENT_CREATE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 생성 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 생성 실패")
    })
    ResponseTemplate<FreePostCommentResponse> create(@Parameter(description = "자유게시글 id 값") Long freePostId,
                                                     @Parameter(description = "자유게시글 댓글 정보") FreePostCommentRequest request);

    @ApiOperation(value = "자유게시글 댓글 조회", notes = SwaggerNote.FREE_POST_COMMENT_FIND)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 조회 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 조회 실패")
    })
    ResponseTemplate<FreePostCommentFindResponse> findByFreePostCommentId(@Parameter(description = "자유게시글 댓글 id 값") Long commentId);

    @ApiOperation(value = "자유게시글의 전체 댓글 조회", notes = SwaggerNote.FREE_POST_COMMENT_FIND_BY_PAGE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 조회 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 조회 실패")
    })
    ResponseTemplate<FreePostCommentFindByFreePostIdAndPageResponse> findByFreePostIdAndPage(@Parameter(description = "자유게시글 id 값") Long freePostId,
                                                                                             @Parameter(description = "페이지 정보") Pageable pageable);

    @ApiOperation(value = "자유게시글 수정", notes = SwaggerNote.FREE_POST_COMMENT_UPDATE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 수정 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 수정 실패")
    })
    ResponseTemplate<FreePostCommentResponse> update(@Parameter(description = "자유게시글 댓글 id 값") Long commentId,
                                                     @Parameter(description = "자유게시글 댓글 정보") FreePostCommentRequest request);

    @ApiOperation(value = "자유게시글 삭제", notes = SwaggerNote.FREE_POST_COMMENT_DELETE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 댓글 삭제 성공"),
            @ApiResponse(code = 400, message = "자유게시글 댓글 삭제 실패")
    })
    ResponseTemplate<FreePostCommentResponse> delete(@Parameter(description = "자유게시글 댓글 id 값") Long commentId);
}
