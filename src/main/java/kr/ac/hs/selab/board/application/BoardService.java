package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.dto.response.BoardsResponse;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.error.exception.common.DuplicationException;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse create(BoardCreateDto dto) {
        validateCreateBoard(dto.getTitle());
        Board board = boardRepository.save(BoardConverter.toBoard(dto));
        return BoardConverter.toBoardResponse(board);
    }

    @Transactional(readOnly = true)
    public BoardResponse find(Long id) {
        Board board = boardRepository.findByDeletedNot(id)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR));
        return BoardConverter.toBoardResponse(board);
    }

    @Transactional(readOnly = true)
    public BoardsResponse findAll() {
        List<Board> boards = boardRepository.findAllByDeletedNot();
        return BoardConverter.toBoardsResponse(boards);
    }

    @Transactional
    public BoardResponse update(BoardUpdateDto dto) {
        Board board = boardRepository.findByDeletedNot(dto.getId())
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR));
        validateUpdateBoard(board.getTitle(), dto.getTitle());

        Board newBoard = board.update(dto.getTitle(), dto.getDescription());
        return BoardConverter.toBoardResponse(newBoard);
    }

    @Transactional
    public BoardResponse delete(Long id) {
        Board board = boardRepository.findByDeletedNot(id)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR))
                .delete();
        return BoardConverter.toBoardResponse(board);
    }

    private void validateCreateBoard(String title) {
        if (boardRepository.existsByTitle(title)) {
            throw new DuplicationException(ErrorMessage.BOARD_TITLE_DUPLICATION_ERROR);
        }
    }

    private void validateUpdateBoard(String oldTitle, String newTitle) {
        if (boardRepository.existsByTitle(newTitle) && !oldTitle.equals(newTitle)) {
            throw new DuplicationException(ErrorMessage.BOARD_TITLE_DUPLICATION_ERROR);
        }
    }
}
