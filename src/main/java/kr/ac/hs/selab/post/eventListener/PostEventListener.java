package kr.ac.hs.selab.post.eventListener;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.post.domain.event.PostEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class PostEventListener {
    private final CommentService commentService;

    @Async
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @TransactionalEventListener
    public void deleteByPost(PostEvent postEvent) {
        var post = postEvent.getPost();
        commentService.deleteByPost(post);
    }
}
