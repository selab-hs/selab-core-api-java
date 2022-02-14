package kr.ac.hs.selab.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BoardResponse {
    @Schema(description = "게시판 ID")
    private final Long id;
}
