package kr.ac.hs.selab.postLike.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.postLike.application.PostLikeService;
import kr.ac.hs.selab.postLike.domain.PostLike;
import kr.ac.hs.selab.postLike.dto.PostLikeDto;
import kr.ac.hs.selab.postLike.dto.PostLikeFindDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class PostLikeFacadeTest {
    @Mock
    private MemberService memberService;

    @Mock
    private PostService postService;

    @Mock
    private PostLikeService postLikeService;

    @InjectMocks
    private PostLikeFacade postLikeFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시글_좋아요_성공() {
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postLike = new PostLike(member.getId(), post.getId());
        var postLikeDto = new PostLikeDto(member.getEmail(), post.getId());

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(postService.findPostById(anyLong()))
                .thenReturn(post);
        Mockito.when(postLikeService.create(anyLong(), anyLong()))
                .thenReturn(postLike);

        // when
        var actual = postLikeFacade.create(postLikeDto);

        // then
        assertEquals(postLike.getId(), actual.getId());
    }

    @Test
    public void 게시글_아이디로_게시글_좋아요_조회_성공() {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postLikes = fixtureMonkey.giveMeBuilder(PostLike.class)
                .set("postId", post.getId())
                .sampleList(10);
        var postLikeFindDto = new PostLikeFindDto(post.getId());

        // mocking
        Mockito.when(postService.findPostById(anyLong()))
                .thenReturn(post);
        Mockito.when(postLikeService.find(anyLong()))
                .thenReturn(postLikes);

        // when
        var actual = postLikeFacade.find(postLikeFindDto);

        // then
        assertEquals(post.getId(), actual.getPostId());
    }

    @Test
    public void 게시글_좋아요_취소_성공() {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);

        // mocking
        Mockito.doNothing()
                .when(postLikeService)
                .delete(post.getId());

        // when
        var actual = postLikeFacade.delete(post.getId());

        // then
        assertEquals(post.getId(), actual.getId());
    }
}
