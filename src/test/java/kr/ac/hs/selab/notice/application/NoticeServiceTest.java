package kr.ac.hs.selab.notice.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeCreateDto;
import kr.ac.hs.selab.notice.dto.NoticeUpdateDto;
import kr.ac.hs.selab.notice.infrastructure.NoticeRepository;
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
public class NoticeServiceTest {
    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private NoticeService noticeService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 공지사항_생성하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(Notice.class);
        var noticeCreateDto = NoticeCreateDto.builder()
                .title(expected.getTitle())
                .content(expected.getContent())
                .image(expected.getImage())
                .build();

        // mocking
        Mockito.when(noticeRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = noticeService.create(noticeCreateDto);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_전체_개수_구하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(Long.class);

        // mocking
        Mockito.when(noticeRepository.countByDeleteFlag(anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = noticeService.count();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 아이디로_공지사항_찾기() {
        // given
        var expected = fixtureMonkey.giveMeOne(Notice.class);

        // mocking
        Mockito.when(noticeRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = noticeService.findById(expected.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 전체_공지사항_페이지로_찾기() {
        // given
        var totalCount = 100L;
        var pageNumber = 1;
        var pageSize = 20;

        var pageable = PageRequest.of(pageNumber, pageSize);
        var notices = fixtureMonkey.giveMe(Notice.class, (int) totalCount);
        var expected = new PageImpl<>(notices, pageable, totalCount);

        // mocking
        Mockito.when(noticeRepository.findByDeleteFlag(anyBoolean(), any()))
                .thenReturn(expected);

        // when
        var actual = noticeService.findAllByPage(pageable);

        // when
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_수정하기() {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var expectedTitle = fixtureMonkey.giveMeOne(String.class);
        var expectedContent = fixtureMonkey.giveMeOne(String.class);
        var expectedImage = fixtureMonkey.giveMeOne(String.class);
        var noticeUpdateDto = NoticeUpdateDto.builder()
                .id(notice.getId())
                .title(expectedTitle)
                .content(expectedContent)
                .image(expectedImage)
                .build();

        // mocking
        Mockito.when(noticeRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(notice));

        // when
        var actual = noticeService.update(noticeUpdateDto);

        // then
        assertEquals(expectedTitle, actual.getTitle());
        assertEquals(expectedContent, actual.getContent());
        assertEquals(expectedImage, actual.getImage());
    }

    @Test
    public void 공지사항_삭제하기() {
        // given
        var expected = fixtureMonkey.giveMeBuilder(Notice.class)
                .set("deleteFlag", false)
                .sample();

        // mocking
        Mockito.when(noticeRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = noticeService.delete(expected.getId());

        // then
        assertTrue(actual.isDeleteFlag());
    }
}