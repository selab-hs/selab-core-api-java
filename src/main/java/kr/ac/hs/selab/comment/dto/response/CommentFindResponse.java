package kr.ac.hs.selab.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentFindResponse {
    @Schema(description = "게시글 id")
    private final Long postId;

    @Schema(description = "작성자 id")
    private final Long memberId;

    @Schema(description = "댓글 id")
    private final Long commentId;

    @Schema(description = "댓글 내용")
    private final String content;

    @Schema(description = "댓글 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "댓글 수정 시간")
    private final LocalDateTime modifiedAt;
}
