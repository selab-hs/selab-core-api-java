package kr.ac.hs.selab.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostFindResponse {
    @Schema(description = "게시판 id")
    private final Long boardId;

    @Schema(description = "작성자 id")
    private final Long memberId;

    @Schema(description = "게시글 id")
    private final Long postId;

    @Schema(description = "게시글 제목")
    private final String title;

    @Schema(description = "게시글 내용")
    private final String content;

    @Schema(description = "게시글 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "게시글 수정 시간")
    private final LocalDateTime modifiedAt;
}