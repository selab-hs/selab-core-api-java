package kr.ac.hs.selab.board.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import kr.ac.hs.selab.board.dto.response.CommentLikeFindResponse;
import kr.ac.hs.selab.board.dto.response.CommentLikeResponse;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import org.springframework.web.bind.annotation.PathVariable;

@Api(tags = "댓글 좋아요 API", description = "Comment Like Controller (미사용)")
public interface CommentLikeSdk {
    @Operation(summary = "댓글 좋아요 생성", description = "댓글에 좋아요를 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "댓글 좋아요 성공"),
            @ApiResponse(code = 400, message = "댓글 좋아요 실패")
    })
    ResponseTemplate<CommentLikeResponse> create(@PathVariable Long commentId);

    @Operation(summary = "댓글에 모든 좋아요 조회", description = "댓글에 모든 좋아요를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "댓글 좋아요 조회 성공"),
            @ApiResponse(code = 400, message = "댓글 좋아요 조회 실패")
    })
    ResponseTemplate<CommentLikeFindResponse> find(@PathVariable Long commentId);

    @Operation(summary = "댓글 좋아요 삭제", description = "댓글에 좋아요를 취소한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "댓글 좋아요 삭제 성공"),
            @ApiResponse(code = 400, message = "댓글 좋아요 삭제 실패")
    })
    ResponseTemplate<CommentLikeResponse> delete(@PathVariable Long id);
}
