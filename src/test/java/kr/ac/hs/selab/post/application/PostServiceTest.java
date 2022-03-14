package kr.ac.hs.selab.post.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostUpdateDto;
import kr.ac.hs.selab.post.infrastructure.PostRepository;
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
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시글_생성_성공() {
        // given
        var memberEmail = fixtureMonkey.giveMeOne(String.class);
        var expected = fixtureMonkey.giveMeOne(Post.class);
        var postCreateDto = PostCreateDto.builder()
                .memberEmail(memberEmail)
                .boardId(expected.getBoardId())
                .title(expected.getTitle())
                .content(expected.getContent())
                .build();

        // mocking
        Mockito.when(postRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = postService.create(postCreateDto, expected.getMemberId(), expected.getBoardId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시판_아이디로_게시글_전체_개수_조회_성공() {
        // given
        var expected = 10L;
        var boardId = fixtureMonkey.giveMeOne(Long.class);

        // mocking
        Mockito.when(postRepository.countByBoardIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = postService.count(boardId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 아이디로_게시글_하나_조회하기_성공() {
        // given
        var expected = fixtureMonkey.giveMeOne(Post.class);

        // mocking
        Mockito.when(postRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = postService.findPostById(expected.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시글_아이디로_게시글_조회하기_성공() {
        // given
        var boardId = fixtureMonkey.giveMeOne(Long.class);
        var expected = fixtureMonkey.giveMeBuilder(Post.class)
                .set("boardId", boardId)
                .sampleList(10);

        // mocking
        Mockito.when(postRepository.findByBoardIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = postService.findPostsByBoardId(boardId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시글_아이디와_페이지로_게시글_조회하기_성공() {
        // given
        var boardId = fixtureMonkey.giveMeOne(Long.class);
        var posts = fixtureMonkey.giveMeBuilder(Post.class)
                .set("boardId", boardId)
                .sampleList(100);

        var pageable = PageRequest.of(1, 20);
        var expected = new PageImpl<>(posts, pageable, posts.size());

        // mocking
        Mockito.when(postRepository.findByBoardIdAndDeleteFlag(anyLong(), anyBoolean(), any()))
                .thenReturn(expected);

        // when
        var actual = postService.findPostsByBoardIdAndPage(boardId, pageable);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시글_수정하기_성공() {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var newTitle = fixtureMonkey.giveMeOne(String.class);
        var newContent = fixtureMonkey.giveMeOne(String.class);
        var postUpdateDto = PostUpdateDto.builder()
                .id(post.getId())
                .title(newTitle)
                .content(newContent)
                .build();

        // mocking
        Mockito.when(postRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(post));

        // when
        var actual = postService.update(postUpdateDto);

        // then
        assertEquals(newTitle, actual.getTitle());
        assertEquals(newContent, actual.getContent());
    }

    @Test
    public void 게시글_아이디로_삭제하기_성공() {
        // given
        var post = fixtureMonkey.giveMeBuilder(Post.class)
                .set("deleteFlag", false)
                .sample();

        // mocking
        Mockito.when(postRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(post));

        // when
        var actual = postService.deleteById(post.getId());

        // then
        assertTrue(actual.isDeleteFlag());
    }

    @Test
    public void 게시판_아이디로_게시글_삭제하기() {
        // given
        var boardId = fixtureMonkey.giveMeOne(Long.class);
        var posts = fixtureMonkey.giveMeBuilder(Post.class)
                .set("boardId", boardId)
                .set("deleteFlag", false)
                .sampleList(20);

        // mocking
        Mockito.when(postRepository.findByBoardIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(posts);

        // when
        postService.deleteByBoardId(boardId);

        // then
        posts.forEach(post -> assertTrue(post.isDeleteFlag()));
    }
}
