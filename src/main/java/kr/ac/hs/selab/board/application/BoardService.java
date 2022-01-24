package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.dto.response.BoardsResponse;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.common.utils.BoardConstants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse create(BoardCreateDto dto) {
        Board board = boardRepository.save(BoardConverter.toBoard(dto));
        return BoardConverter.toBoardResponse(board);
    }

    public BoardResponse find(Long id) {
        Board board = boardRepository.find(id, BoardConstants.NOT_DELETED_BOARD)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR));
        return BoardConverter.toBoardResponse(board);
    }

    public BoardsResponse findAll() {
        List<Board> boards = boardRepository.findAll(BoardConstants.NOT_DELETED_BOARD);
        return BoardConverter.toBoardsResponse(boards);
    }

    @Transactional
    public BoardResponse update(BoardUpdateDto dto) {
        Board board = boardRepository.find(dto.getId(), BoardConstants.NOT_DELETED_BOARD)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR))
                .update(dto.getTitle(), dto.getDescription());
        return BoardConverter.toBoardResponse(board);
    }

    @Transactional
    public BoardResponse delete(Long id) {
        Board board = boardRepository.find(id, BoardConstants.NOT_DELETED_BOARD)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR))
                .delete();
        return BoardConverter.toBoardResponse(board);
    }
}
