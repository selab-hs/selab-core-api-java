package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
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
    public Board create(BoardCreateDto dto) {
        return boardRepository.save(BoardConverter.toBoard(dto));
    }

    public Board findById(Long id) {
        return boardRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.BOARD_NOT_EXISTS_ERROR));
    }

    public List<Board> findAll() {
        return boardRepository.findByDeleteFlag(Constants.NOT_DELETED);
    }

    @Transactional
    public Board update(BoardUpdateDto dto) {
        return findById(dto.getId()).update(dto.getTitle(), dto.getDescription());
    }

    @Transactional
    public Board delete(Long id) {
        return findById(id).delete();
    }
}
