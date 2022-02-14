package kr.ac.hs.selab.board.event;

import kr.ac.hs.selab.board.domain.BoardEvent;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class BoardEventListener {
    private final CommentService commentService;
    private final PostService postService;

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void removeBoard(BoardEvent boardEvent) {
        var board = boardEvent.getBoard();
        commentService.deleteByPosts(
                postService.findPostByBoard(board)
        );
        postService.deleteByBoard(board);
    }
}
