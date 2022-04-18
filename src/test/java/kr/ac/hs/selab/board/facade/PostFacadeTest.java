package kr.ac.hs.selab.board.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.application.PostService;
import kr.ac.hs.selab.board.converter.PostConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.Post;
import kr.ac.hs.selab.board.dto.PostCreateDto;
import kr.ac.hs.selab.board.dto.PostFindByBoardAndPageDto;
import kr.ac.hs.selab.board.dto.PostUpdateDto;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PostFacadeTest {
    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private MemberService memberService;

    @Mock
    private BoardService boardService;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostFacade postFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시글_생성_성공() {   // TODO stack over flow error 해결
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var board = fixtureMonkey.giveMeOne(Board.class);
        var post = fixtureMonkey.giveMeBuilder(Post.class)
                .set("memberId", member.getId())
                .set("boardId", board.getId())
                .sample();
        var postCreateDto = PostCreateDto.builder()
                .memberEmail(member.getEmail())
                .boardId(post.getBoardId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(boardService.findById(anyLong()))
                .thenReturn(board);
        Mockito.when(postService.create(any(), anyLong(), anyLong()))
                .thenReturn(post);

        // when
        var actual = postFacade.create(postCreateDto);

        // then
//        assertEquals(post.getId(), actual.getPostId());
    }

    @Test
    public void 게시글_아이디로_게시글_하나_조회_성공() {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);

        // mocking
        Mockito.when(postService.findPostById(anyLong()))
                .thenReturn(post);

        // when
        var actual = postFacade.findPostResponseById(post.getId());

        // then
        assertEquals(post.getId(), actual.getPostId());
    }

    @Test
    public void 게시판_아이디로_게시글_전체_조회_성공() {
        // given
        var totalCount = 100L;
        var pageNumber = 1;
        var pageSize = 20;

        var board = fixtureMonkey.giveMeOne(Board.class);
        var posts = fixtureMonkey.giveMeBuilder(Post.class)
                .set("boardId", board.getId())
                .sampleList((int) totalCount);

        var pageable = PageRequest.of(pageNumber, pageSize);
        var postPage = new PageImpl<>(posts, pageable, totalCount);
        var postFindByBoardAndPageDto = new PostFindByBoardAndPageDto(board.getId(), pageable);

        var expected = PostConverter.toPostFindByBoardResponse(
                postFindByBoardAndPageDto,
                totalCount,
                postPage
        );

        // mocking
        Mockito.when(boardService.findById(anyLong()))
                .thenReturn(board);
        Mockito.when(postService.count(anyLong()))
                .thenReturn((long) posts.size());
        Mockito.when(postService.findPostsByBoardIdAndPage(anyLong(), any()))
                .thenReturn(postPage);

        // when
        var actual = postFacade.findPostsResponseByBoardId(postFindByBoardAndPageDto);

        // then
        assertEquals(expected.getBoardId(), actual.getBoardId());
        assertEquals(expected.getTotalCount(), actual.getTotalCount());
        assertEquals(expected.getPageNumber(), actual.getPageNumber());
        assertEquals(expected.getPageSize(), actual.getPageSize());
        assertEquals(expected.getSort(), actual.getSort());
        IntStream.range(0, (int) totalCount)
                .forEach(i -> {
                    assertEquals(expected.getPosts().get(i).getMemberId(), actual.getPosts().get(i).getMemberId());
                    assertEquals(expected.getPosts().get(i).getPostId(), actual.getPosts().get(i).getPostId());
                    assertEquals(expected.getPosts().get(i).getTitle(), actual.getPosts().get(i).getTitle());
                    assertEquals(expected.getPosts().get(i).getContent(), actual.getPosts().get(i).getContent());
                    assertEquals(expected.getPosts().get(i).getCreatedAt(), actual.getPosts().get(i).getCreatedAt());
                    assertEquals(expected.getPosts().get(i).getModifiedAt(), actual.getPosts().get(i).getModifiedAt());
                });
    }

    @Test
    public void 게시글_수정_성공() {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postUpdateDto = PostUpdateDto.builder()
                .id(post.getId())
                .title(fixtureMonkey.giveMeOne(String.class))
                .content(fixtureMonkey.giveMeOne(String.class))
                .build();

        // mocking
        Mockito.when(postService.update(any()))
                .thenReturn(post);

        // when
        var actual = postFacade.update(postUpdateDto);

        // then
        assertEquals(post.getId(), actual.getPostId());
    }

    @Test
    public void 게시글_삭제_성공() {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);

        // mocking
        Mockito.when(postService.deleteById(anyLong()))
                .thenReturn(post);

        // when
        var acutal = postFacade.delete(post.getId());

        // then
        assertEquals(post.getId(), acutal.getPostId());
    }
}
