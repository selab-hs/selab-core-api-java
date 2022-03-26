package kr.ac.hs.selab.free_post_comment.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post_comment.domain.FreePostComment;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentCreateDto;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentFindByFreePostIdAndPageDto;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentUpdateDto;
import kr.ac.hs.selab.free_post_comment.infrastructure.FreePostCommentRepository;
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
public class FreePostCommentServiceTest {
    @Mock
    private FreePostCommentRepository freePostCommentRepository;

    @InjectMocks
    private FreePostCommentService freePostCommentService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 자유게시글_댓글_생성하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(FreePostComment.class);
        var dto = FreePostCommentCreateDto.builder()
                .memberId(expected.getMemberId())
                .freePostId(expected.getFreePostId())
                .freePostCommentContent(expected.getContent())
                .build();

        // mocking
        Mockito.when(freePostCommentRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = freePostCommentService.create(dto);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 자유게시글_아이디로_댓글_개수_조회하기() {
        // given
        var expected = 10L;
        var freePostId = fixtureMonkey.giveMeOne(Long.class);

        // mocking
        Mockito.when(freePostCommentRepository.countByFreePostIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = freePostCommentService.count(freePostId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 자유게시글_댓글_아이디로_댓글_하나_조회하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(FreePostComment.class);

        // mocking
        Mockito.when(freePostCommentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = freePostCommentService.findByFreePostCommentId(expected.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 자유게시글_아이디로_댓글_조회하기() {
        // given
        var freePostId = fixtureMonkey.giveMeOne(Long.class);
        var expected = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("freePostId", freePostId)
                .sampleList(10);

        // mocking
        Mockito.when(freePostCommentRepository.findByFreePostIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = freePostCommentService.findByFreePostId(freePostId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 자유게시글_아이디와_페이지로_댓글_조회하기() {
        // given
        var freePostId = fixtureMonkey.giveMeOne(Long.class);
        var comments = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("freePostId", freePostId)
                .sampleList(100);

        var pageable = PageRequest.of(1, 20);
        var dto = new FreePostCommentFindByFreePostIdAndPageDto(freePostId, pageable);

        var expected = new PageImpl<>(comments, pageable, comments.size());

        // mocking
        Mockito.when(freePostCommentRepository.findByFreePostIdAndDeleteFlag(anyLong(), anyBoolean(), any()))
                .thenReturn(expected);

        // when
        var actual = freePostCommentService.findByFreePostIdAndPageDto(dto);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 자유게시글_댓글_수정하기() {
        // given
        var comment = fixtureMonkey.giveMeOne(FreePostComment.class);
        var newContent = fixtureMonkey.giveMeOne(String.class);
        var dto = new FreePostCommentUpdateDto(comment.getId(), newContent);

        // mocking
        Mockito.when(freePostCommentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(comment));

        // when
        var actual = freePostCommentService.update(dto);

        // then
        assertEquals(newContent, actual.getContent());
    }

    @Test
    public void 자유게시글_댓글_아이디로_삭제하기() {
        // given
        var comment = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("deleteFlag", false)
                .sample();

        // mocking
        Mockito.when(freePostCommentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(comment));

        // when
        var actual = freePostCommentService.deleteByFreePostCommentId(comment.getId());

        // then
        assertTrue(actual.isDeleteFlag());
    }

    @Test
    public void 자유게시글_아이디로_댓글_삭제하기() {
        // given
        var freePostId = fixtureMonkey.giveMeOne(Long.class);
        var comments = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("freePostId", freePostId)
                .set("deleteFlag", false)
                .sampleList(20);

        // mocking
        Mockito.when(freePostCommentRepository.findByFreePostIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(comments);

        // when
        freePostCommentService.deleteByFreePostId(freePostId);

        // then
        comments.forEach(comment -> assertTrue(comment.isDeleteFlag()));
    }

    @Test
    public void 자유게시글_여러_개에_연관된_댓글_삭제하기() {
        // given
        var freePosts = fixtureMonkey.giveMe(FreePost.class, 10);
        var comments = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("noticeId", freePosts.get(0).getId())
                .set("deleteFlag", false)
                .sampleList(20);

        // mocking
        Mockito.when(freePostCommentRepository.findByFreePostIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(comments);

        // when
        freePostCommentService.deleteByFreePosts(freePosts);

        // then
        comments.forEach(comment -> assertTrue(comment.isDeleteFlag()));
    }
}
