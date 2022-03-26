package kr.ac.hs.selab.notice_comment.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice_comment.domain.NoticeComment;
import kr.ac.hs.selab.notice_comment.dto.NoticeCommentCreateDto;
import kr.ac.hs.selab.notice_comment.dto.NoticeCommentFindByNoticeIdAndPageDto;
import kr.ac.hs.selab.notice_comment.dto.NoticeCommentUpdateDto;
import kr.ac.hs.selab.notice_comment.infrastructure.NoticeCommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class NoticeCommentServiceTest {
    @Mock
    private NoticeCommentRepository noticeCommentRepository;

    @InjectMocks
    private NoticeCommentService noticeCommentService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 공지사항에_댓글_생성하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(NoticeComment.class);
        var dto = NoticeCommentCreateDto.builder()
                .memberId(expected.getMemberId())
                .noticeId(expected.getNoticeId())
                .noticeCommentContent(expected.getContent())
                .build();

        // mocking
        Mockito.when(noticeCommentRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = noticeCommentService.create(dto);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_아이디로_댓글_개수_조회하기() {
        // given
        var expected = 10L;
        var noticeId = fixtureMonkey.giveMeOne(Long.class);

        // mocking
        Mockito.when(noticeCommentRepository.countByNoticeIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = noticeCommentService.count(noticeId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_댓글_아이디로_댓글_하나_조회하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(NoticeComment.class);

        // mocking
        Mockito.when(noticeCommentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = noticeCommentService.findByNoticeCommentId(expected.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_아이디로_댓글_조회하기() {
        // given
        var noticeId = fixtureMonkey.giveMeOne(Long.class);
        var expected = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("noticeId", noticeId)
                .sampleList(10);

        // mocking
        Mockito.when(noticeCommentRepository.findByNoticeIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = noticeCommentService.findByNoticeId(noticeId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_아이디와_페이지로_댓글_조회하기() {
        // given
        var noticeId = fixtureMonkey.giveMeOne(Long.class);
        var comments = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("noticeId", noticeId)
                .sampleList(100);

        var pageable = PageRequest.of(1, 20);
        var dto = new NoticeCommentFindByNoticeIdAndPageDto(noticeId, pageable);

        var expected = new PageImpl<>(comments, pageable, comments.size());

        // mocking
        Mockito.when(noticeCommentRepository.findByNoticeIdAndDeleteFlag(anyLong(), anyBoolean(), any()))
                .thenReturn(expected);

        // when
        var actual = noticeCommentService.findByNoticeCommentFindByNoticeIdAndPageDto(dto);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항의_댓글_수정하기() {
        // given
        var comment = fixtureMonkey.giveMeOne(NoticeComment.class);
        var newContent = fixtureMonkey.giveMeOne(String.class);
        var dto = new NoticeCommentUpdateDto(comment.getId(), newContent);

        // mocking
        Mockito.when(noticeCommentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(comment));

        // when
        var actual = noticeCommentService.update(dto);

        // then
        assertEquals(newContent, actual.getContent());
    }

    @Test
    public void 공지사항의_댓글_아이디로_삭제하기() {
        // given
        var comment = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("deleteFlag", false)
                .sample();

        // mocking
        Mockito.when(noticeCommentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(comment));

        // when
        var actual = noticeCommentService.deleteByNoticeCommentId(comment.getId());

        // then
        assertTrue(actual.isDeleteFlag());
    }

    @Test
    public void 공지사항_아이디로_댓글_삭제하기() {
        // given
        var noticeId = fixtureMonkey.giveMeOne(Long.class);
        var comments = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("noticeId", noticeId)
                .set("deleteFlag", false)
                .sampleList(20);

        // mocking
        Mockito.when(noticeCommentRepository.findByNoticeIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(comments);

        // when
        noticeCommentService.deleteByNoticeId(noticeId);

        // then
        comments.forEach(comment -> assertTrue(comment.isDeleteFlag()));
    }

    @Test
    public void 공지사항_여러_개에_연관된_댓글_삭제하기() {
        // given
        var notices = fixtureMonkey.giveMe(Notice.class, 10);
        var comments = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("noticeId", notices.get(0).getId())
                .set("deleteFlag", false)
                .sampleList(20);

        // mocking
        Mockito.when(noticeCommentRepository.findByNoticeIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(comments);

        // when
        noticeCommentService.deleteByNotices(notices);

        // then
        comments.forEach(comment -> assertTrue(comment.isDeleteFlag()));
    }
}
