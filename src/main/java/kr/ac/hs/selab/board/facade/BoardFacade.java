package kr.ac.hs.selab.board.facade;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class BoardFacade {
    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public BoardResponse deleteById(Long id) {
        Board board = boardService.deleteById(id);

        postService.findPostByBoard(board)
                .forEach(commentService::deleteByPost);

        postService.deleteByBoard(board);

        return BoardConverter.toBoardResponse(board);
    }
}
