package kr.ac.hs.selab.comment.eventListener;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.domain.event.CommentEvent;
import kr.ac.hs.selab.commentLike.application.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class CommentEventListener {
    private final CommentLikeService commentLikeService;

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void deleteByComment(CommentEvent commentEvent) {
        Comment comment = commentEvent.getComment();
        commentLikeService.deleteByComment(comment);
    }
}
