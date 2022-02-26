package kr.ac.hs.selab.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class CommentFindByPostAndPageResponse {
    @Schema(description = "게시글 id")
    private final Long postId;

    @Schema(description = "댓글 전체 개수")
    private final Long totalCount;

    @Schema(description = "댓글 페이지")
    private final int pageNumber;

    @Schema(description = "한 페이지에 가져올 댓글 수")
    private final int pageSize;

    @Schema(description = "댓글 정렬 기준")
    private final String sort;

    @Schema(description = "댓글 전체 리스트")
    private final List<CommentInnerResponse> comments;

    @Builder
    @Getter
    public static class CommentInnerResponse {
        @Schema(description = "작성자 이메일")
        private final String memberEmail;

        @Schema(description = "댓글 id")
        private final Long commentId;

        @Schema(description = "댓글 내용")
        private final String content;

        @Schema(description = "댓글 생성 시간")
        private final LocalDateTime createdAt;

        @Schema(description = "댓글 수정 시간")
        private final LocalDateTime modifiedAt;
    }
}
