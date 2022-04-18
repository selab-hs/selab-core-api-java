package kr.ac.hs.selab.board.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.board.converter.PostConverter;
import kr.ac.hs.selab.board.domain.Post;
import kr.ac.hs.selab.board.dto.PostFindByBoardAndPageDto;
import kr.ac.hs.selab.board.dto.request.PostRequest;
import kr.ac.hs.selab.board.dto.response.PostResponse;
import kr.ac.hs.selab.board.facade.PostFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(useDefaultFilters = false)
@AutoConfigureMockMvc(addFilters = false)
@Import(PostController.class)
@ExtendWith(MockitoExtension.class)
public class PostControllerTest {
    @MockBean
    private PostFacade postFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시글_생성_성공() throws Exception {  // TODO stack over flow error 해결
        // given
        var post = fixtureMonkey.giveMeBuilder(Post.class)
                .set("title", "심심해용")
                .set("content", "심심하다심심해")
                .sample();
        var postRequest = new PostRequest(post.getTitle(), post.getContent());
        var postResponse = new PostResponse(post.getId());

        // mocking
        Mockito.when(postFacade.create(any()))
                .thenReturn(postResponse);

        // when, then
//        mockMvc.perform(
//                        post("/api/v1/boards/{boardId}/posts", post.getBoardId())
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .characterEncoding("utf-8")
//                                .content(objectMapper.writeValueAsString(postRequest))
//                )
//                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_제목이_null_값인_경우_게시글_생성_실패() throws Exception {
        // given
        var post = fixtureMonkey.giveMeBuilder(Post.class)
                .set("title", null)
                .set("content", "심심하다심심해")
                .sample();
        var postRequest = new PostRequest(post.getTitle(), post.getContent());
        var postResponse = new PostResponse(post.getId());

        // mocking
        Mockito.when(postFacade.create(any()))
                .thenReturn(postResponse);

        // when, then
        mockMvc.perform(
                        post("/api/v1/boards/{boardId}/posts", post.getBoardId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(postRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시글_내용이_수정_값인_경우_게시글_생성_실패() throws Exception {
        // given
        var post = fixtureMonkey.giveMeBuilder(Post.class)
                .set("title", "심심해용")
                .set("content", "")
                .sample();
        var postRequest = new PostRequest(post.getTitle(), post.getContent());
        var postResponse = new PostResponse(post.getId());

        // mocking
        Mockito.when(postFacade.create(any()))
                .thenReturn(postResponse);

        // when, then
        mockMvc.perform(
                        post("/api/v1/boards/{boardId}/posts", post.getBoardId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(postRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시글_아이디_값으로_게시글_조회_성공() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postFindResponse = PostConverter.toPostFindResponse(post);

        // mocking
        Mockito.when(postFacade.findPostResponseById(anyLong()))
                .thenReturn(postFindResponse);

        // when, then
        mockMvc.perform(get("/api/v1/posts/{postId}", post.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시판_아이디_값과_페이지로_게시글_조회_성공() throws Exception {
        // given
        var totalCount = 100L;
        var pageNumber = 1;
        var pageSize = 20;
        var boardId = fixtureMonkey.giveMeOne(Long.class);

        var posts = fixtureMonkey.giveMeBuilder(Post.class)
                .set("boardId", boardId)
                .sampleList((int) totalCount);
        var pageable = PageRequest.of(pageNumber, pageSize);
        var postPage = new PageImpl<>(posts, pageable, totalCount);
        var postFindByBoardAndPageDto = new PostFindByBoardAndPageDto(boardId, pageable);

        var postFindByBoardIdAndPageResponse = PostConverter.toPostFindByBoardResponse(
                postFindByBoardAndPageDto,
                totalCount,
                postPage
        );

        // mocking
        Mockito.when(postFacade.findPostsResponseByBoardId(any()))
                .thenReturn(postFindByBoardIdAndPageResponse);

        // when, then
        mockMvc.perform(get("/api/v1/boards/{boardId}/posts", boardId))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_수정_성공() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postRequest = new PostRequest("심심합니다잉", "심심해유심심해");
        var postResponse = new PostResponse(post.getId());

        // mocking
        Mockito.when(postFacade.update(any()))
                .thenReturn(postResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/posts/{id}", post.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(postRequest))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_제목이_null_값인_경우_게시글_수정_실패() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postRequest = new PostRequest(null, "심심해유심심해");
        var postResponse = new PostResponse(post.getId());

        // mocking
        Mockito.when(postFacade.update(any()))
                .thenReturn(postResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/posts/{id}", post.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(postRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시글_내용이_빈_값인_경우_게시글_수정_실패() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postRequest = new PostRequest("심심합니다잉", "");
        var postResponse = new PostResponse(post.getId());

        // mocking
        Mockito.when(postFacade.update(any()))
                .thenReturn(postResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/posts/{id}", post.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(postRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시글_삭제_성공() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(Post.class);
        var postResponse = new PostResponse(post.getId());

        // mocking
        Mockito.when(postFacade.delete(anyLong()))
                .thenReturn(postResponse);

        // when, then
        mockMvc.perform(patch("/api/v1/posts/{id}", post.getId()))
                .andExpect(status().isOk());
    }
}
