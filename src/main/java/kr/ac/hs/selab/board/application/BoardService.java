package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.dto.response.BoardsResponse;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse createByBoardCreateDto(BoardCreateDto dto) {
        Board board = boardRepository.save(BoardConverter.toBoard(dto));
        return BoardConverter.toBoardResponse(board);
    }

    public BoardResponse findBoardResponseById(Long id) {
        Board board = findBoardById(id);
        return BoardConverter.toBoardResponse(board);
    }

    public Board findBoardById(Long id) {
        return boardRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR));
    }

    public BoardsResponse findBoardsResponse() {
        List<Board> boards = boardRepository.findByDeleteFlag(Constants.NOT_DELETED);
        return BoardConverter.toBoardsResponse(boards);
    }

    @Transactional
    public BoardResponse updateByBoardUpdateDto(BoardUpdateDto dto) {
        Board board = findBoardById(dto.getId()).update(dto.getTitle(), dto.getDescription());
        return BoardConverter.toBoardResponse(board);
    }

    @Transactional
    public Board deleteById(Long id) {
        return findBoardById(id).delete();
    }
}
