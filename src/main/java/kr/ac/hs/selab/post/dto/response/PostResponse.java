package kr.ac.hs.selab.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostResponse {
    @Schema(description = "회원 정보")
    private final Member member;

    @Schema(description = "게시판 정보")
    private final Board board;

    @Schema(description = "게시글 제목")
    private final String title;

    @Schema(description = "게시글 내용")
    private final String content;

    @Schema(description = "게시글 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "게시글 수정 시간")
    private final LocalDateTime modifiedAt;
}
