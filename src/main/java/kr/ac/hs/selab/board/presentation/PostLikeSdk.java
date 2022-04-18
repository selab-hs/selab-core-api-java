package kr.ac.hs.selab.board.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.board.dto.response.PostLikeFindResponse;
import kr.ac.hs.selab.board.dto.response.PostLikeResponse;
import org.springframework.web.bind.annotation.PathVariable;

@Api(tags = "게시글 좋아요 관리", description = "미사용 API")
public interface PostLikeSdk {
    @Operation(summary = "게시글 좋아요 생성", description = "게시글에 좋아요를 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 좋아요 성공"),
            @ApiResponse(code = 400, message = "게시글 좋아요 실패")
    })
    ResponseTemplate<PostLikeResponse> create(@PathVariable Long postId);

    @Operation(summary = "게시글에 모든 좋아요 조회", description = "게시글에 모든 좋아요를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 좋아요 조회 성공"),
            @ApiResponse(code = 400, message = "게시글 좋아요 조회 실패")
    })
    ResponseTemplate<PostLikeFindResponse> find(@PathVariable Long postId);

    @Operation(summary = "게시글 좋아요 삭제", description = "게시글에 좋아요를 취소한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시글 좋아요 삭제 성공"),
            @ApiResponse(code = 400, message = "게시글 좋아요 삭제 실패")
    })
    ResponseTemplate<PostLikeResponse> delete(@PathVariable Long id);
}
