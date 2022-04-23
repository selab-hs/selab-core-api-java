package kr.ac.hs.selab.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BoardFindAllResponse {
    @Schema(description = "게시판 전체 개수")
    private final Long totalCount;

    @Schema(description = "게시판 전체 리스트")
    private final List<BoardFindResponse> boards;
}