package kr.ac.hs.selab.board.facade;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class BoardFacade {
    private final BoardService boardService;
    private final PostService postService;

    @Transactional
    public BoardResponse delete(Long id) {
        Board board = boardService.delete(id);
        postService.delete(board);
        return BoardConverter.toBoardResponse(board);
    }
}
