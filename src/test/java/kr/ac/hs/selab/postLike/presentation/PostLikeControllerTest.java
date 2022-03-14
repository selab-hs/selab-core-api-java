package kr.ac.hs.selab.postLike.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.postLike.converter.PostLikeConverter;
import kr.ac.hs.selab.postLike.domain.PostLike;
import kr.ac.hs.selab.postLike.dto.response.PostLikeResponse;
import kr.ac.hs.selab.postLike.facade.PostLikeFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(useDefaultFilters = false)
@AutoConfigureMockMvc(addFilters = false)
@Import(PostLikeController.class)
@ExtendWith(MockitoExtension.class)
public class PostLikeControllerTest {
    @MockBean
    private PostLikeFacade postLikeFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시글_좋아요_생성_성공() throws Exception {  // TODO member 없음 error
        // given
        var postLike = fixtureMonkey.giveMeOne(PostLike.class);
        var postLikeResponse = new PostLikeResponse(postLike.getId());

        // mocking
        Mockito.when(postLikeFacade.create(any()))
                .thenReturn(postLikeResponse);

        // when, then
//        mockMvc.perform(post("/api/v1/posts/{postId}/likes", postLike.getPostId()))
//                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_아이디로_게시글_좋아요_조회_성공() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postLikes = fixtureMonkey.giveMeBuilder(PostLike.class)
                .set("postId", post.getId())
                .sampleList(10);
        var postLikeFindResponse = PostLikeConverter.toPostLikeFindResponse(post.getId(), postLikes);

        // mocking
        Mockito.when(postLikeFacade.find(any()))
                .thenReturn(postLikeFindResponse);

        // when, then
        mockMvc.perform(get("/api/v1/posts/{postId}/likes", post.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_좋아요_취소_성공() throws Exception {
        // given
        var postLike = fixtureMonkey.giveMeOne(PostLike.class);
        var postLikeResponse = new PostLikeResponse(postLike.getId());

        // mocking
        Mockito.when(postLikeFacade.delete(anyLong()))
                .thenReturn(postLikeResponse);

        // when, then
        mockMvc.perform(delete("/api/v1/posts/likes/{id}", postLike.getPostId()))
                .andExpect(status().isOk());
    }
}
