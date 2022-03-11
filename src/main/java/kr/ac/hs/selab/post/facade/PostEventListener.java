package kr.ac.hs.selab.post.facade;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.commentLike.application.CommentLikeService;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.domain.event.PostEvent;
import kr.ac.hs.selab.postLike.application.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class PostEventListener {
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;
    private final PostLikeService postLikeService;

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void deleteByPost(PostEvent postEvent) {
        Post post = postEvent.getPost();
        postLikeService.deleteByPostId(post.getId());
        commentLikeService.deleteByComments(
                commentService.findCommentsByPostId(post.getId())
        );
        commentService.deleteByPost(post.getId());
    }
}
