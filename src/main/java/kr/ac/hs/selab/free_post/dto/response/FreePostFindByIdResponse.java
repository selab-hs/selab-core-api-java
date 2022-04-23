package kr.ac.hs.selab.free_post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FreePostFindByIdResponse {
    @Schema(description = "작성자 아이디")
    private final Long memberId;

    @Schema(description = "자유게시글 아이디")
    private final Long freePostId;

    @Schema(description = "자유게시글 제목")
    private final String title;

    @Schema(description = "자유게시글 내용")
    private final String content;

    @Schema(description = "자유게시글 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "자유게시글 수정 시간")
    private final LocalDateTime modifiedAt;
}