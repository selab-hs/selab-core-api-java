package kr.ac.hs.selab.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BoardFindResponse {
    @Schema(description = "게시판 제목")
    private final String title;

    @Schema(description = "게시판 설명")
    private final String description;

    @Schema(description = "게시판 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "게시판 수정 시간")
    private final LocalDateTime modifiedAt;
}