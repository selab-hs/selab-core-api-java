package kr.ac.hs.selab.comment.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentFindByPostIdAndPageDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.commentLike.application.CommentLikeService;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CommentFacadeTest {
    @Mock
    private MemberService memberService;

    @Mock
    private PostService postService;

    @Mock
    private CommentService commentService;

    @Mock
    private CommentLikeService commentLikeService;

    @InjectMocks
    private CommentFacade commentFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 댓글_생성_성공() {
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var post = fixtureMonkey.giveMeOne(Post.class);
        var comment = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("memberId", member.getId())
                .set("postId", post.getId())
                .sample();
        var commentCreateDto = CommentCreateDto.builder()
                .memberEmail(member.getEmail())
                .postId(comment.getPostId())
                .content(comment.getContent())
                .build();

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(postService.findPostById(anyLong()))
                .thenReturn(post);
        Mockito.when(commentService.create(anyLong(), anyLong(), any()))
                .thenReturn(comment);

        // when
        var actual = commentFacade.create(commentCreateDto);

        // then
        assertEquals(comment.getId(), actual.getCommentId());
    }

    @Test
    public void 댓글_아이디로_댓글_하나_조회_성공() {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var expected = CommentConverter.toCommentFindResponse(comment);

        // mocking
        Mockito.when(commentService.findCommentById(anyLong()))
                .thenReturn(comment);

        // when
        var actual = commentFacade.findCommentResponseById(comment.getId());

        // then
        assertEquals(expected.getPostId(), actual.getPostId());
        assertEquals(expected.getMemberId(), actual.getMemberId());
        assertEquals(expected.getCommentId(), actual.getCommentId());
        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    public void 게시글_아이디로_댓글_조회_성공() {
        // given
        var totalCount = 100L;
        var pageNumber = 1;
        var pageSize = 20;

        var post = fixtureMonkey.giveMeOne(Post.class);
        var comments = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("postId", post.getId())
                .sampleList((int) totalCount);

        var pageable = PageRequest.of(pageNumber, pageSize);
        var commentPage = new PageImpl<>(comments, pageable, totalCount);
        var commentFindByPostIdAndPageDto = new CommentFindByPostIdAndPageDto(post.getId(), pageable);
        var expected = CommentConverter.toCommentFindByPostIdAndPageResponse(
                commentFindByPostIdAndPageDto,
                totalCount,
                commentPage
        );

        // mocking
        Mockito.when(postService.findPostById(anyLong()))
                .thenReturn(post);
        Mockito.when(commentService.count(anyLong()))
                .thenReturn((long) comments.size());
        Mockito.when(commentService.findCommentsByPostId(anyLong(), any()))
                .thenReturn(commentPage);

        // when
        var actual = commentFacade.findCommentsResponseByPostId(commentFindByPostIdAndPageDto);

        // then
        assertEquals(expected.getPostId(), actual.getPostId());
        assertEquals(expected.getTotalCount(), actual.getTotalCount());
        assertEquals(expected.getPageNumber(), actual.getPageNumber());
        assertEquals(expected.getPageSize(), actual.getPageSize());
        assertEquals(expected.getSort(), actual.getSort());
        IntStream.range(0, (int) totalCount)
                .forEach(i -> {
                    assertEquals(expected.getComments().get(i).getMemberId(), actual.getComments().get(i).getMemberId());
                    assertEquals(expected.getComments().get(i).getCommentId(), actual.getComments().get(i).getCommentId());
                    assertEquals(expected.getComments().get(i).getContent(), actual.getComments().get(i).getContent());
                    assertEquals(expected.getComments().get(i).getCreatedAt(), actual.getComments().get(i).getCreatedAt());
                    assertEquals(expected.getComments().get(i).getModifiedAt(), actual.getComments().get(i).getModifiedAt());
                });
    }

    @Test
    public void 댓글_수정_성공() {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var commentUpdateDto = new CommentUpdateDto(
                comment.getId(),
                fixtureMonkey.giveMeOne(String.class)
        );

        // mocking
        Mockito.when(commentService.update(any()))
                .thenReturn(comment);

        // when
        var actual = commentFacade.update(commentUpdateDto);

        // then
        assertEquals(comment.getId(), actual.getCommentId());
    }

    @Test
    public void 댓글_삭제_성공() {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);

        // mocking
        Mockito.when(commentService.deleteByComment(anyLong()))
                .thenReturn(comment);
        Mockito.doNothing()
                .when(commentLikeService)
                .deleteByCommentId(comment.getId());

        // when
        var actual = commentFacade.delete(comment.getId());

        // then
        assertEquals(comment.getId(), actual.getCommentId());
    }
}
