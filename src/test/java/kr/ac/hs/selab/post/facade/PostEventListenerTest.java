package kr.ac.hs.selab.post.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment_like.application.CommentLikeService;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.domain.event.PostEvent;
import kr.ac.hs.selab.postLike.application.PostLikeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostEventListenerTest {
    @Mock
    private CommentService commentService;

    @Mock
    private CommentLikeService commentLikeService;

    @Mock
    private PostLikeService postLikeService;

    @InjectMocks
    private PostEventListener postEventListener;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시글_삭제시_관련_객체_전부_삭제_성공() {
        // given
        var post = fixtureMonkey.giveMeBuilder(Post.class)
                .set("deleteFlag", false)
                .sample();
        var comments = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("postId", post.getId())
                .set("deleteFlag", false)
                .sampleList(10);

        // mocking
        Mockito.doNothing()
                .when(postLikeService)
                .deleteByPostId(post.getId());
        Mockito.when(commentService.findCommentsByPostId(post.getId()))
                .thenReturn(comments);
        Mockito.doNothing()
                .when(commentLikeService)
                .deleteByComments(comments);
        Mockito.doNothing()
                .when(commentService)
                .deleteByPost(post.getId());

        // when
        postEventListener.deleteByPost(PostEvent.of(post));

        // then
        // TODO void test 연구하기
        // TODO async, transaction test 연구하기
    }
}
