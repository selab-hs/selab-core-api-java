package kr.ac.hs.selab.comment.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.infrastructure.CommentRepository;
import kr.ac.hs.selab.post.domain.Post;
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
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 댓글_생성_성공() {
        // given
        var expected = fixtureMonkey.giveMeOne(Comment.class);

        // mocking
        Mockito.when(commentRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = commentService.create(expected.getMemberId(), expected.getPostId(), expected.getContent());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시글_아이디로_댓글_전체_개수_조회_성공() {
        // given
        var expected = 10L;
        var postId = fixtureMonkey.giveMeOne(Long.class);

        // mocking
        Mockito.when(commentRepository.countByPostIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = commentService.count(postId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 아이디로_댓글_하나_조회_성공() {
        // given
        var expected = fixtureMonkey.giveMeOne(Comment.class);

        // mocking
        Mockito.when(commentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = commentService.findCommentById(expected.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시글_아이디로_댓글_조회_성공() {
        // given
        var postId = fixtureMonkey.giveMeOne(Long.class);
        var expected = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("postId", postId)
                .sampleList(10);

        // mocking
        Mockito.when(commentRepository.findByPostIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = commentService.findCommentsByPostId(postId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시글_아이디와_페이지로_댓글_조회_성공() {
        // given
        var postId = fixtureMonkey.giveMeOne(Long.class);
        var comments = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("postId", postId)
                .sampleList(100);

        var pageable = PageRequest.of(1, 20);
        var expected = new PageImpl<>(comments, pageable, comments.size());

        // mocking
        Mockito.when(commentRepository.findByPostIdAndDeleteFlag(anyLong(), anyBoolean(), any()))
                .thenReturn(expected);

        // when
        var actual = commentService.findCommentsByPostId(postId, pageable);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 댓글_수정_성공() {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var newContent = fixtureMonkey.giveMeOne(String.class);
        var postUpdateDto = new CommentUpdateDto(comment.getId(), newContent);

        // mocking
        Mockito.when(commentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(comment));

        // when
        var actual = commentService.update(postUpdateDto);

        // then
        assertEquals(newContent, actual.getContent());
    }

    @Test
    public void 댓글_아이디로_삭제_성공() {
        // given
        var comment = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("deleteFlag", false)
                .sample();

        // mocking
        Mockito.when(commentRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(comment));

        // when
        var actual = commentService.deleteByComment(comment.getId());

        // then
        assertTrue(actual.isDeleteFlag());
    }

    @Test
    public void 게시글_아이디로_댓글_삭제_성공() {
        // given
        var postId = fixtureMonkey.giveMeOne(Long.class);
        var comments = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("postId", postId)
                .set("deleteFlag", false)
                .sampleList(20);

        // mocking
        Mockito.when(commentRepository.findByPostIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(comments);

        // when
        commentService.deleteByPost(postId);

        // then
        comments.forEach(comment -> assertTrue(comment.isDeleteFlag()));
    }

    @Test
    public void 게시글_여러_개에_연관된_댓글_삭제_성공() {
        // given
        var posts = fixtureMonkey.giveMe(Post.class, 10);
        var comments = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("postId", posts.get(0).getId())
                .set("deleteFlag", false)
                .sampleList(20);

        // mocking
        Mockito.when(commentRepository.findByPostIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(comments);

        // when
        commentService.deleteByPosts(posts);

        // then
        comments.forEach(comment -> assertTrue(comment.isDeleteFlag()));
    }
}
