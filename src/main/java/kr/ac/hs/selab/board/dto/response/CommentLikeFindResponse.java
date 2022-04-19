package kr.ac.hs.selab.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Getter
public class CommentLikeFindResponse {
    @Schema(description = "댓글 id")
    private final Long commentId;

    @Schema(description = "댓글 좋아요 전체 개수")
    private final Long totalCount;

    @Schema(description = "댓글 좋아요 전체")
    private final List<CommentLikeInnerResponse> commentLikes;

    @RequiredArgsConstructor
    @Getter
    public static class CommentLikeInnerResponse {
        @Schema(description = "댓글 좋아요 id")
        private final Long id;

        @Schema(description = "댓글 좋아요한 회원 id")
        private final Long memberId;
    }
}
