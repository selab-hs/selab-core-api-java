package kr.ac.hs.selab.free_post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FreePostCommentFindResponse {
    @Schema(description = "자유게시판 id")
    private final Long freePostId;

    @Schema(description = "작성자 id")
    private final Long memberId;

    @Schema(description = "공지사항의 댓글 id")
    private final Long freePostCommentId;

    @Schema(description = "공지사항의 댓글 내용")
    private final String freePostCommentContent;

    @Schema(description = "공지사항의 댓글 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "공지사항의 댓글 수정 시간")
    private final LocalDateTime modifiedAt;
}
