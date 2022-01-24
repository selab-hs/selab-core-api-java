package kr.ac.hs.selab.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BoardsResponse {
    @Schema(description = "게시판 전체 리스트")
    private final List<BoardResponse> boardResponses;

    public static BoardsResponse of(List<BoardResponse> boardResponses) {
        return new BoardsResponse(boardResponses);
    }
}
