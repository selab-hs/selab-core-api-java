package kr.ac.hs.selab.notice.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.notice.application.NoticeLikeService;
import kr.ac.hs.selab.notice.converter.NoticeLikeConverter;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.domain.NoticeLike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NoticeLikeFacadeTest {
    @Mock
    private MemberService memberService;

    @Mock
    private NoticeLikeService noticeLikeService;

    @InjectMocks
    private NoticeLikeFacade noticeLikeFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 공지사항_좋아요_하기() {
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var expected = new NoticeLike(member.getId(), notice.getId());

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(noticeLikeService.create(any()))
                .thenReturn(expected);

        // when
        var actual = noticeLikeFacade.create(member.getEmail(), notice.getId());

        // then
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void 공지사항_아이디로_공지사항_좋아요_조회하기() {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var likes = fixtureMonkey.giveMeBuilder(NoticeLike.class)
                .set("noticeId", notice.getId())
                .sampleList(10);
        var expected = NoticeLikeConverter.toNoticeLikeFindResponse(notice.getId(), likes);

        // mocking
        Mockito.when(noticeLikeService.find(anyLong()))
                .thenReturn(likes);

        // when
        var actual = noticeLikeFacade.find(notice.getId());

        // then
        assertEquals(expected.getNoticeId(), actual.getNoticeId());
        assertEquals(expected.getTotalCount(), actual.getTotalCount());
        IntStream.range(0, 10)
                .forEach(i -> assertEquals(expected.getNoticeLikes().get(i).getId(), actual.getNoticeLikes().get(i).getId()));
    }

    @Test
    public void 공지사항_좋아요_취소하기() {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);

        // mocking
        Mockito.doNothing()
                .when(noticeLikeService)
                .deleteByNoticeLikeId(notice.getId());

        // when
        var actual = noticeLikeFacade.delete(notice.getId());

        // then
        assertEquals(notice.getId(), actual.getId());
    }
}
