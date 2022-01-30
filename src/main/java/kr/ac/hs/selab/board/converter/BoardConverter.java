package kr.ac.hs.selab.board.converter;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.request.BoardRequest;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.dto.response.BoardsResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BoardConverter {
    public Board toBoard(BoardCreateDto dto) {
        return Board.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public BoardResponse toBoardResponse(Board board) {
        return BoardResponse.builder()
                .title(board.getTitle())
                .description(board.getDescription())
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .build();
    }

    public BoardsResponse toBoardsResponse(List<Board> boards) {
        List<BoardResponse> boardResponses = boards.stream()
                .map(BoardConverter::toBoardResponse)
                .collect(Collectors.toList());
        return new BoardsResponse(boardResponses);
    }

    public BoardUpdateDto toBoardUpdateDto(Long id, BoardRequest request) {
        return BoardUpdateDto.builder()
                .id(id)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }
}
