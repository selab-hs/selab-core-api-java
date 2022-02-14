package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.response.BoardFindAllResponse;
import kr.ac.hs.selab.board.dto.response.BoardFindResponse;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.common.utils.Constants;
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
        return new BoardResponse(board.getId());
    }

    public BoardFindResponse findBoardResponseById(Long id) {
        Board board = findBoardById(id);
        return BoardConverter.toBoardResponse(board);
    }

    public Board findBoardById(Long id) {
        return boardRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR));
    }

    public BoardFindAllResponse findBoardsResponse() {
        Long totalCount = boardRepository.countByDeleteFlag(Constants.NOT_DELETED);
        List<Board> boards = boardRepository.findByDeleteFlag(Constants.NOT_DELETED);
        return BoardConverter.toBoardsResponse(totalCount, boards);
    }

    @Transactional
    public BoardResponse update(BoardUpdateDto dto) {
        Board board = findBoardById(dto.getId()).update(dto.getTitle(), dto.getDescription());
        return new BoardResponse(board.getId());
    }

    @Transactional
    public Board delete(Long id) {
        return findBoardById(id).delete();
    }
}
