package kr.ac.hs.selab.notice_like.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.notice_like.domain.NoticeLike;
import kr.ac.hs.selab.notice_like.dto.NoticeLikeDto;
import kr.ac.hs.selab.notice_like.infrastructure.NoticeLikeRepository;
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
public class NoticeLikeServiceTest {
    @Mock
    private NoticeLikeRepository noticeLikeRepository;

    @InjectMocks
    private NoticeLikeService noticeLikeService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 공지사항_좋아요_하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(NoticeLike.class);
        var dto = new NoticeLikeDto(expected.getMemberId(), expected.getNoticeId());

        // mocking
        Mockito.when(noticeLikeRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = noticeLikeService.create(dto);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_아이디로_공지사항_좋아요_조회하기() {
        // given
        var noticeId = fixtureMonkey.giveMeOne(Long.class);
        var expected = fixtureMonkey.giveMeBuilder(NoticeLike.class)
                .set("noticeId", noticeId)
                .sampleList(10);

        // mocking
        Mockito.when(noticeLikeRepository.findByNoticeId(anyLong()))
                .thenReturn(expected);

        // when
        var actual = noticeLikeService.find(noticeId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_좋아요_취소하기() {
        // given
        var like = fixtureMonkey.giveMeOne(NoticeLike.class);

        // mocking
        Mockito.doNothing()
                .when(noticeLikeRepository)
                .deleteById(like.getId());

        // when
        noticeLikeService.deleteByNoticeLikeId(like.getId());
    }

    @Test
    public void 공지사항_아이디로_공지사항_좋아요_모두_취소하기() {
        // given
        var noticeId = fixtureMonkey.giveMeOne(Long.class);
        var likes = fixtureMonkey.giveMeBuilder(NoticeLike.class)
                .set("noticeId", noticeId)
                .sampleList(10);

        // mocking
        Mockito.when(noticeLikeRepository.findByNoticeId(noticeId))
                .thenReturn(likes);
        Mockito.doNothing()
                .when(noticeLikeRepository)
                .deleteAll(likes);

        // when
        noticeLikeService.deleteByNoticeId(noticeId);
    }
}
