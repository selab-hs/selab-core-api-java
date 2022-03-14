package kr.ac.hs.selab.commentLike.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.commentLike.application.CommentLikeService;
import kr.ac.hs.selab.commentLike.domain.CommentLike;
import kr.ac.hs.selab.commentLike.dto.CommentLikeDto;
import kr.ac.hs.selab.commentLike.dto.CommentLikeFindDto;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CommentLikeFacadeTest {
    @Mock
    private MemberService memberService;

    @Mock
    private CommentService commentService;

    @Mock
    private CommentLikeService commentLikeService;

    @InjectMocks
    private CommentLikeFacade commentLikeFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 댓글_좋아요_성공() {
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var commentLike = new CommentLike(member.getId(), comment.getId());
        var commentLikeDto = new CommentLikeDto(member.getEmail(), comment.getId());

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(commentService.findCommentById(anyLong()))
                .thenReturn(comment);
        Mockito.when(commentLikeService.create(anyLong(), anyLong()))
                .thenReturn(commentLike);

        // when
        var actual = commentLikeFacade.create(commentLikeDto);

        // then
        assertEquals(commentLike.getId(), actual.getId());
    }

    @Test
    public void 댓글_아이디로_댓글_좋아요_조회_성공() {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var commentLikes = fixtureMonkey.giveMeBuilder(CommentLike.class)
                .set("commentId", comment.getId())
                .sampleList(10);
        var commentLikeFindDto = new CommentLikeFindDto(comment.getId());

        // mocking
        Mockito.when(commentService.findCommentById(anyLong()))
                .thenReturn(comment);
        Mockito.when(commentLikeService.find(anyLong()))
                .thenReturn(commentLikes);

        // when
        var actual = commentLikeFacade.find(commentLikeFindDto);

        // then
        assertEquals(comment.getId(), actual.getCommentId());
    }

    @Test
    public void 댓글_좋아요_취소_성공() {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);

        // mocking
        Mockito.doNothing()
                .when(commentLikeService)
                .deleteById(comment.getId());

        // when
        var actual = commentLikeFacade.delete(comment.getId());

        // then
        assertEquals(comment.getId(), actual.getId());
    }
}
