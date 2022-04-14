package kr.ac.hs.selab.post_like.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Getter
public class PostLikeFindResponse {
    @Schema(description = "게시글 id")
    private final Long postId;

    @Schema(description = "게시글 좋아요 전체 개수")
    private final Long totalCount;

    @Schema(description = "게시글 좋아요 전체")
    private final List<PostLikeInnerResponse> postLikes;

    @RequiredArgsConstructor
    @Getter
    public static class PostLikeInnerResponse {
        @Schema(description = "게시글 좋아요 id")
        private final Long id;

        @Schema(description = "게시글 좋아요한 유저 id")
        private final Long memberId;
    }
}
