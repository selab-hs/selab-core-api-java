package kr.ac.hs.selab.board.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.event.BoardEvent;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment_like.application.CommentLikeService;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post_like.application.PostLikeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class BoardEventListenerTest {
    @Mock
    private PostService postService;

    @Mock
    private CommentService commentService;

    @Mock
    private PostLikeService postLikeService;

    @Mock
    private CommentLikeService commentLikeService;

    @InjectMocks
    private BoardEventListener boardEventListener;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시판_삭제시_연관_객체_전부_삭제_성공() {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var posts = fixtureMonkey.giveMeBuilder(Post.class)
                .set("boardId", board.getId())
                .sampleList(10);
        var comments = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("postId", posts.get(0).getId())
                .sampleList(10);

        // mocking
        Mockito.when(postService.findPostsByBoardId(anyLong()))
                .thenReturn(posts);
        Mockito.when(commentService.findCommentsByPostId(anyLong()))
                .thenReturn(comments);
        Mockito.doNothing()
                .when(commentLikeService)
                .deleteByComments(comments);
        Mockito.doNothing()
                .when(commentService)
                .deleteByPosts(posts);
        Mockito.doNothing()
                .when(postLikeService)
                .deleteByPostId(posts.get(0).getId());
        Mockito.doNothing()
                .when(postService)
                .deleteByBoardId(board.getId());

        // when
        boardEventListener.deleteByBoard(BoardEvent.of(board));

        // then
        // TODO void test 연구하기
        // TODO async, transaction test 연구하기
    }
}
