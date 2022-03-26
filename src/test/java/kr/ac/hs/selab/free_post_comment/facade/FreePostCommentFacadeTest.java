package kr.ac.hs.selab.free_post_comment.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post_comment.application.FreePostCommentService;
import kr.ac.hs.selab.free_post_comment.converter.FreePostCommentConverter;
import kr.ac.hs.selab.free_post_comment.domain.FreePostComment;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentFindByFreePostIdAndPageDto;
import kr.ac.hs.selab.free_post_comment.dto.request.FreePostCommentRequest;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class FreePostCommentFacadeTest {
    @Mock
    private MemberService memberService;

    @Mock
    private FreePostCommentService freePostCommentService;

    @InjectMocks
    private FreePostCommentFacade freePostCommentFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 자유게시글_댓글_생성하기() {
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var comment = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("memberId", member.getId())
                .sample();
        var request = new FreePostCommentRequest(comment.getContent());

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(freePostCommentService.create(any()))
                .thenReturn(comment);

        // when
        var actual = freePostCommentFacade.create(comment.getFreePostId(), member.getEmail(), request);

        // then
        assertEquals(comment.getId(), actual.getFreePostCommentId());
    }

    @Test
    public void 자유게시글_댓글_아이디로_댓글_조회하기() {
        // given
        var comment = fixtureMonkey.giveMeOne(FreePostComment.class);
        var expected = FreePostCommentConverter.toFreePostCommentFindResponse(comment);

        // mocking
        Mockito.when(freePostCommentService.findByFreePostCommentId(anyLong()))
                .thenReturn(comment);

        // when
        var actual = freePostCommentFacade.findByFreePostCommentId(comment.getId());

        // then
        assertEquals(expected.getFreePostCommentId(), actual.getFreePostCommentId());
    }

    @Test
    public void 자유게시글_아이디와_페이지로_댓글_조회하기() {
        // given
        var totalCount = 100L;
        var freePost = fixtureMonkey.giveMeOne(FreePost.class);
        var comments = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("freePostId", freePost.getId())
                .sampleList((int) totalCount);

        var pageable = PageRequest.of(1, 20);
        var commentPage = new PageImpl<>(comments, pageable, totalCount);
        var dto = new FreePostCommentFindByFreePostIdAndPageDto(freePost.getId(), pageable);
        var expected = FreePostCommentConverter.toFreePostCommentFindByFreePostIdAndPageResponse(
                dto,
                totalCount,
                commentPage
        );

        // mocking
        Mockito.when(freePostCommentService.count(anyLong()))
                .thenReturn((long) comments.size());
        Mockito.when(freePostCommentService.findByFreePostIdAndPageDto(any()))
                .thenReturn(commentPage);

        // when
        var actual = freePostCommentFacade.findByFreePostIdAndPage(freePost.getId(), pageable);

        // then
        assertEquals(expected.getFreePostId(), actual.getFreePostId());
    }

    @Test
    public void 자유게시글_댓글_수정하기() {
        // given
        var comment = fixtureMonkey.giveMeOne(FreePostComment.class);
        var request = new FreePostCommentRequest(comment.getContent());

        // mocking
        Mockito.when(freePostCommentService.update(any()))
                .thenReturn(comment);

        // when
        var actual = freePostCommentFacade.update(comment.getId(), request);

        // then
        assertEquals(comment.getId(), actual.getFreePostCommentId());
    }

    @Test
    public void 자유게시글_댓글_삭제하기() {
        // given
        var comment = fixtureMonkey.giveMeOne(FreePostComment.class);

        // mocking
        Mockito.when(freePostCommentService.deleteByFreePostCommentId(anyLong()))
                .thenReturn(comment);

        // when
        var actual = freePostCommentFacade.delete(comment.getId());

        // then
        assertEquals(comment.getId(), actual.getFreePostCommentId());
    }
}
