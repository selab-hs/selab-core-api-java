package kr.ac.hs.selab.commentLike.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.commentLike.domain.CommentLike;
import kr.ac.hs.selab.commentLike.infrastructure.CommentLikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class CommentLikeServiceTest {
    @Mock
    private CommentLikeRepository commentLikeRepository;

    @InjectMocks
    private CommentLikeService commentLikeService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 댓글_좋아요_성공() {
        // given
        var expected = fixtureMonkey.giveMeOne(CommentLike.class);

        // mocking
        Mockito.when(commentLikeRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = commentLikeService.create(expected.getMemberId(), expected.getMemberId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 댓글_아이디로_댓글_좋아요_조회_성공() {
        // given
        var commentId = fixtureMonkey.giveMeOne(Long.class);
        var expected = fixtureMonkey.giveMeBuilder(CommentLike.class)
                .set("commentId", commentId)
                .sampleList(10);

        // mocking
        Mockito.when(commentLikeRepository.findByCommentId(anyLong()))
                .thenReturn(expected);

        // when
        var actual = commentLikeService.find(commentId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 댓글_좋아요_아이디로_댓글_좋아요_취소_성공() {
        // given
        var commentLike = fixtureMonkey.giveMeOne(CommentLike.class);

        // mocking
        Mockito.doNothing()
                .when(commentLikeRepository)
                .deleteById(commentLike.getId());

        // when
        commentLikeService.deleteById(commentLike.getId());

        // then
        // TODO void test 연구하기
    }

    @Test
    public void 댓글_아이디로_댓글_좋아요_모두_취소_성공() {
        // given
        var commentId = fixtureMonkey.giveMeOne(Long.class);
        var commentLikes = fixtureMonkey.giveMeBuilder(CommentLike.class)
                .set("commentId", commentId)
                .sampleList(20);

        // mocking
        Mockito.when(commentLikeRepository.findByCommentId(anyLong()))
                .thenReturn(commentLikes);
        Mockito.doNothing()
                .when(commentLikeRepository)
                .deleteAll(commentLikes);

        // when
        commentLikeService.deleteByCommentId(commentId);

        // then
        // TODO void test 연구하기
    }

    @Test
    public void 댓글_리스트로_댓글_좋아요_모두_취소_성공() {
        // given
        var comments = fixtureMonkey.giveMe(Comment.class, 10);
        var commentLikes = fixtureMonkey.giveMeBuilder(CommentLike.class)
                .set("commentId", comments.get(0).getId())
                .sampleList(10);

        // mocking
        Mockito.when(commentLikeRepository.findByCommentId(anyLong()))
                .thenReturn(commentLikes);
        Mockito.doNothing()
                .when(commentLikeRepository)
                .deleteAll(commentLikes);

        // when
        commentLikeService.deleteByComments(comments);

        // then
        // TODO void test 연구하기
    }
}
