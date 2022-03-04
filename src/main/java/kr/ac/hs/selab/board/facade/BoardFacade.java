package kr.ac.hs.selab.board.facade;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.event.BoardEvent;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.response.BoardFindAllResponse;
import kr.ac.hs.selab.board.dto.response.BoardFindResponse;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BoardFacade {
    private final ApplicationEventPublisher publisher;
    private final BoardService boardService;

    @Transactional
    public BoardResponse create(BoardCreateDto dto) {
        var board = boardService.create(dto);
        return new BoardResponse(board.getId());
    }

    @Transactional
    public BoardFindResponse findBoardResponseById(Long id) {
        var board = boardService.findById(id);
        return BoardConverter.toBoardResponse(board);
    }

    public BoardFindAllResponse findBoardFindAllResponse() {
        List<Board> boards = boardService.findAll();
        return BoardConverter.toBoardsResponse(boards);
    }

    @Transactional
    public BoardResponse update(BoardUpdateDto dto) {
        var board = boardService.update(dto);
        return new BoardResponse(board.getId());
    }

    @Transactional
    public BoardResponse delete(Long id) {
        var board = boardService.delete(id);
        publisher.publishEvent(BoardEvent.of(board));

        return new BoardResponse(board.getId());
    }
}