package kr.ac.hs.selab.postLike.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.postLike.domain.PostLike;
import kr.ac.hs.selab.postLike.infrastructure.PostLikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class PostLikeServiceTest {
    @Mock
    private PostLikeRepository postLikeRepository;

    @InjectMocks
    private PostLikeService postLikeService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시글_좋아요_성공() {
        // given
        var expected = fixtureMonkey.giveMeOne(PostLike.class);

        // mocking
        Mockito.when(postLikeRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = postLikeService.create(expected.getMemberId(), expected.getPostId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시글_아이디로_게시글_좋아요_조회_성공() {
        // given
        var postId = fixtureMonkey.giveMeOne(Long.class);
        var expected = fixtureMonkey.giveMeBuilder(PostLike.class)
                .set("postId", postId)
                .sampleList(10);

        // mocking
        Mockito.when(postLikeRepository.findByPostId(anyLong()))
                .thenReturn(expected);

        // when
        var actual = postLikeService.find(postId);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 게시글_좋아요_취소_성공() {
        // given
        var postLike = fixtureMonkey.giveMeOne(PostLike.class);

        // mocking
        Mockito.doNothing()
                .when(postLikeRepository)
                .deleteById(postLike.getId());

        // when
        postLikeService.delete(postLike.getId());

        // then
        // TODO void test 연구하기
    }

    @Test
    public void 게시글_아이디로_게시글_좋아요_모두_취소_성공() {
        // given
        var postId = fixtureMonkey.giveMeOne(Long.class);
        var postLikes = fixtureMonkey.giveMeBuilder(PostLike.class)
                .set("postId", postId)
                .sampleList(20);

        // mocking
        Mockito.when(postLikeRepository.findByPostId(anyLong()))
                .thenReturn(postLikes);
        Mockito.doNothing()
                .when(postLikeRepository)
                .deleteAll(postLikes);

        // when
        postLikeService.deleteByPostId(postId);

        // then
        // TODO void test 연구하기
    }
}
