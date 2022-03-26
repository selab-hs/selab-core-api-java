package kr.ac.hs.selab.notice_comment.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice_comment.application.NoticeCommentService;
import kr.ac.hs.selab.notice_comment.converter.NoticeCommentConverter;
import kr.ac.hs.selab.notice_comment.domain.NoticeComment;
import kr.ac.hs.selab.notice_comment.dto.NoticeCommentFindByNoticeIdAndPageDto;
import kr.ac.hs.selab.notice_comment.dto.request.NoticeCommentRequest;
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
public class NoticeCommentFacadeTest {
    @Mock
    private MemberService memberService;

    @Mock
    private NoticeCommentService noticeCommentService;

    @InjectMocks
    private NoticeCommentFacade noticeCommentFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 공지사항_댓글_생성하기() {
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var comment = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("memberId", member.getId())
                .sample();
        var request = new NoticeCommentRequest(comment.getContent());

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(noticeCommentService.create(any()))
                .thenReturn(comment);

        // when
        var actual = noticeCommentFacade.create(comment.getNoticeId(), member.getEmail(), request);

        // then
        assertEquals(comment.getId(), actual.getNoticeCommentId());
    }

    @Test
    public void 공지사항_댓글_아이디로_댓글_조회하기() {
        // given
        var comment = fixtureMonkey.giveMeOne(NoticeComment.class);
        var expected = NoticeCommentConverter.toNoticeCommentFindResponse(comment);

        // mocking
        Mockito.when(noticeCommentService.findByNoticeCommentId(anyLong()))
                .thenReturn(comment);

        // when
        var actual = noticeCommentFacade.findByNoticeCommentId(comment.getId());

        // then
        assertEquals(expected.getNoticeCommentId(), actual.getNoticeCommentId());
    }

    @Test
    public void 공지사항_아이디와_페이지로_댓글_조회하기() {
        // given
        var totalCount = 100L;
        var pageNumber = 1;
        var pageSize = 20;

        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var comments = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("noticeId", notice.getId())
                .sampleList((int) totalCount);

        var pageable = PageRequest.of(pageNumber, pageSize);
        var commentPage = new PageImpl<>(comments, pageable, totalCount);
        var dto = new NoticeCommentFindByNoticeIdAndPageDto(notice.getId(), pageable);
        var expected = NoticeCommentConverter.toNoticeCommentFindByNoticeIdAndPageResponse(
                dto,
                totalCount,
                commentPage
        );

        // mocking
        Mockito.when(noticeCommentService.count(anyLong()))
                .thenReturn((long) comments.size());
        Mockito.when(noticeCommentService.findByNoticeCommentFindByNoticeIdAndPageDto(any()))
                .thenReturn(commentPage);

        // when
        var actual = noticeCommentFacade.findByNoticeIdAndPage(notice.getId(), pageable);

        // then
        assertEquals(expected.getNoticeId(), actual.getNoticeId());
    }

    @Test
    public void 공지사항_댓글_수정하기() {
        // given
        var comment = fixtureMonkey.giveMeOne(NoticeComment.class);
        var request = new NoticeCommentRequest(comment.getContent());

        // mocking
        Mockito.when(noticeCommentService.update(any()))
                .thenReturn(comment);

        // when
        var actual = noticeCommentFacade.update(comment.getId(), request);

        // then
        assertEquals(comment.getId(), actual.getNoticeCommentId());
    }

    @Test
    public void 공지사항_댓글_삭제하기() {
        // given
        var comment = fixtureMonkey.giveMeOne(NoticeComment.class);

        // mocking
        Mockito.when(noticeCommentService.deleteByNoticeCommentId(anyLong()))
                .thenReturn(comment);

        // when
        var actual = noticeCommentFacade.delete(comment.getId());

        // then
        assertEquals(comment.getId(), actual.getNoticeCommentId());
    }
}
