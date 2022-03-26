package kr.ac.hs.selab.free_post.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post.dto.FreePostCreateDto;
import kr.ac.hs.selab.free_post.dto.FreePostUpdateDto;
import kr.ac.hs.selab.free_post.infrastructure.FreePostRepository;
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
public class FreePostServiceTest {
    @Mock
    private FreePostRepository freePostRepository;

    @InjectMocks
    private FreePostService freePostService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 자유게시글_생성하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(FreePost.class);
        var dto = FreePostCreateDto.builder()
                .memberId(expected.getMemberId())
                .title(expected.getTitle())
                .content(expected.getContent())
                .build();

        // mocking
        Mockito.when(freePostRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = freePostService.create(dto);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 자유게시글_삭제되지_않은_것_개수_구하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(Long.class);

        // mocking
        Mockito.when(freePostRepository.countByDeleteFlag(anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = freePostService.count();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 아이디로_자유게시글_조회하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(FreePost.class);

        // mocking
        Mockito.when(freePostRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = freePostService.findById(expected.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 공지사항_페이지로_찾기() {
        // given
        var totalCount = 100L;
        var pageNumber = 1;
        var pageSize = 20;

        var pageable = PageRequest.of(pageNumber, pageSize);
        var notices = fixtureMonkey.giveMe(FreePost.class, (int) totalCount);
        var expected = new PageImpl<>(notices, pageable, totalCount);

        // mocking
        Mockito.when(freePostRepository.findByDeleteFlag(anyBoolean(), any()))
                .thenReturn(expected);

        // when
        var actual = freePostService.findByPage(pageable);

        // when
        assertEquals(expected, actual);
    }

    @Test
    public void 자유게시글_수정하기() {
        // given
        var notice = fixtureMonkey.giveMeOne(FreePost.class);
        var expectedTitle = fixtureMonkey.giveMeOne(String.class);
        var expectedContent = fixtureMonkey.giveMeOne(String.class);
        var dto = FreePostUpdateDto.builder()
                .id(notice.getId())
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        // mocking
        Mockito.when(freePostRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(notice));

        // when
        var actual = freePostService.update(dto);

        // then
        assertEquals(expectedTitle, actual.getTitle());
        assertEquals(expectedContent, actual.getContent());
    }

    @Test
    public void 자유게시글_삭제하기() {
        // given
        var expected = fixtureMonkey.giveMeBuilder(FreePost.class)
                .set("deleteFlag", false)
                .sample();

        // mocking
        Mockito.when(freePostRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = freePostService.delete(expected.getId());

        // then
        assertTrue(actual.isDeleteFlag());
    }
}