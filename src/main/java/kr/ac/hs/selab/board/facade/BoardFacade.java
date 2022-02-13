package kr.ac.hs.selab.board.facade;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.BoardEvent;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BoardFacade {
    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public BoardResponse deleteById(Long id) {
        Board board = boardService.deleteById(id);

        publisher.publishEvent(BoardEvent.of(board));
        System.out.println("진짜 끝!!!");

        return BoardConverter.toBoardResponse(board);
    }
}
