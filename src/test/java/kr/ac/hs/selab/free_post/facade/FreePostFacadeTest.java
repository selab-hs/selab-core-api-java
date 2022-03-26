package kr.ac.hs.selab.free_post.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.free_post.application.FreePostService;
import kr.ac.hs.selab.free_post.converter.FreePostConverter;
import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post.dto.FreePostFindByPageDto;
import kr.ac.hs.selab.free_post.dto.request.FreePostRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostResponse;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
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
public class FreePostFacadeTest {
    @Mock
    private MemberService memberService;

    @Mock
    private FreePostService freePostService;

    @InjectMocks
    private FreePostFacade freePostFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 자유게시글_생성하기() {
        // given
        var member = fixtureMonkey.giveMeOne(Member.class);
        var post = fixtureMonkey.giveMeBuilder(FreePost.class)
                .set("memberId", member.getId())
                .sample();
        var request = new FreePostRequest(
                post.getTitle(),
                post.getContent()
        );
        var expected = new FreePostResponse(post.getId());

        // mocking
        Mockito.when(memberService.findByEmail(anyString()))
                .thenReturn(member);
        Mockito.when(freePostService.create(any()))
                .thenReturn(post);

        // when
        var actual = freePostFacade.create(member.getEmail(), request);

        // then
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void 아이디로_자유게시글_조회하기() {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var expected = FreePostConverter.toFreePostFindByIdResponse(post);

        // mocking
        Mockito.when(freePostService.findById(anyLong()))
                .thenReturn(post);

        // when
        var actual = freePostFacade.findById(post.getId());

        // then
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    public void 자유게시글_페이지로_찾기() {
        // given
        var totalCount = 100L;
        var pageable = PageRequest.of(1, 20);
        var posts = fixtureMonkey.giveMe(FreePost.class, (int) totalCount);
        var postPage = new PageImpl<>(posts, pageable, totalCount);

        var dto = FreePostFindByPageDto.builder()
                .totalCount(totalCount)
                .pageable(pageable)
                .freePosts(postPage)
                .build();
        var expected = FreePostConverter.toFreePostFindByPageResponse(dto);

        // mocking
        Mockito.when(freePostService.count())
                .thenReturn(totalCount);
        Mockito.when(freePostService.findByPage(any()))
                .thenReturn(postPage);

        // when
        var actual = freePostFacade.findByPage(pageable);

        // when
        assertEquals(expected.getTotalCount(), actual.getTotalCount());
        IntStream.range(0, 10)
                .forEach(i -> assertEquals(expected.getFreePosts().get(i).getTitle(), actual.getFreePosts().get(i).getTitle()));
    }

    @Test
    public void 자유게시글_수정하기() {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var request = new FreePostRequest(
                fixtureMonkey.giveMeOne(String.class),
                fixtureMonkey.giveMeOne(String.class)
        );
        var expected = new FreePostResponse(post.getId());

        // mocking
        Mockito.when(freePostService.update(any()))
                .thenReturn(post);

        // when
        var actual = freePostFacade.update(post.getId(), request);

        // then
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void 자유게시글_삭제하기() {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var expected = new FreePostResponse(post.getId());

        // mocking
        Mockito.when(freePostService.delete(anyLong()))
                .thenReturn(post);

        // when
        var actual = freePostFacade.delete(expected.getId());

        // then
        assertEquals(expected.getId(), actual.getId());
    }
}
