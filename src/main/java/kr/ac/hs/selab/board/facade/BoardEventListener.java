package kr.ac.hs.selab.board.facade;

import kr.ac.hs.selab.board.application.CommentLikeService;
import kr.ac.hs.selab.board.application.CommentService;
import kr.ac.hs.selab.board.application.PostLikeService;
import kr.ac.hs.selab.board.application.PostService;
import kr.ac.hs.selab.board.domain.Comment;
import kr.ac.hs.selab.board.domain.Post;
import kr.ac.hs.selab.board.domain.event.BoardEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BoardEventListener {
    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeService postLikeService;
    private final CommentLikeService commentLikeService;

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void deleteByBoard(BoardEvent boardEvent) {
        var board = boardEvent.getBoard();
        List<Post> posts = postService.findPostsByBoardId(board.getId());

        posts.forEach(post -> {
            List<Comment> comments = commentService.findCommentsByPostId(post.getId());
            commentLikeService.deleteByComments(comments);
        });
        commentService.deleteByPosts(posts);

        posts.forEach(post -> postLikeService.deleteByPostId(post.getId()));
        postService.deleteByBoardId(board.getId());
    }
}