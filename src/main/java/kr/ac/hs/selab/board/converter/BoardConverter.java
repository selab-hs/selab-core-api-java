package kr.ac.hs.selab.board.converter;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.request.BoardRequest;
import kr.ac.hs.selab.board.dto.response.BoardFindAllResponse;
import kr.ac.hs.selab.board.dto.response.BoardFindResponse;
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

    public BoardFindResponse toBoardResponse(Board board) {
        return BoardFindResponse.builder()
                .title(board.getTitle())
                .description(board.getDescription())
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .build();
    }

    public BoardFindAllResponse toBoardsResponse(Long totalCount, List<Board> boards) {
        List<BoardFindResponse> boardResponses = boards.stream()
                .map(BoardConverter::toBoardResponse)
                .collect(Collectors.toList());
        return new BoardFindAllResponse(totalCount, boardResponses);
    }

    public BoardUpdateDto toBoardUpdateDto(Long id, BoardRequest request) {
        return BoardUpdateDto.builder()
                .id(id)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }
}
