package kr.ac.hs.selab.board.eventListener;

import kr.ac.hs.selab.board.domain.event.BoardEvent;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class BoardEventListener {
    private final PostService postService;
    private final CommentService commentService;

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void deleteByBoard(BoardEvent boardEvent) {
        var board = boardEvent.getBoard();
        commentService.deleteByPosts(
                postService.findPostsByBoard(board)
        );
        postService.deleteByBoard(board);
    }
}
