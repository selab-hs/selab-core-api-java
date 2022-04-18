package kr.ac.hs.selab.free_post.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.free_post.converter.FreePostCommentConverter;
import kr.ac.hs.selab.free_post.domain.FreePostComment;
import kr.ac.hs.selab.free_post.dto.FreePostCommentFindByFreePostIdAndPageDto;
import kr.ac.hs.selab.free_post.dto.request.FreePostCommentRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostCommentResponse;
import kr.ac.hs.selab.free_post.facade.FreePostCommentFacade;
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
@Import(FreePostCommentController.class)
@ExtendWith(MockitoExtension.class)
public class FreePostCommentControllerTest {
    @MockBean
    private FreePostCommentFacade freePostCommentFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 자유게시글_댓글_생성하기() throws Exception {  // TODO member 없음 error
//        // given
//        var comment = fixtureMonkey.giveMeOne(FreePostComment.class);
//        var request = new FreePostCommentRequest(comment.getContent());
//        var response = new FreePostCommentResponse(comment.getId());
//
//        // mocking
//        Mockito.when(freePostCommentFacade.create(anyLong(), anyString(), any()))
//                .thenReturn(response);
//
//        // when, then
//        mockMvc.perform(
//                        post("/api/v1/free-posts/{freePostId}/free-post-comments", comment.getId())
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .characterEncoding("utf-8")
//                                .content(objectMapper.writeValueAsString(request))
//                )
//                .andExpect(status().isOk());
    }

    @Test
    public void 자유게시글_댓글_아이디_값으로_댓글_조회하기() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(FreePostComment.class);
        var response = FreePostCommentConverter.toFreePostCommentFindResponse(comment);

        // mocking
        Mockito.when(freePostCommentFacade.findByFreePostCommentId(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/free-post-comments/{commentId}", comment.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 자유게시글_아이디_값과_페이지로_댓글_조회하기() throws Exception {
        // given
        var totalCount = 100L;
        var freePostId = fixtureMonkey.giveMeOne(Long.class);

        var comments = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("freePostId", freePostId)
                .sampleList((int) totalCount);
        var pageable = PageRequest.of(1, 20);
        var commentPage = new PageImpl<>(comments, pageable, totalCount);
        var dto = new FreePostCommentFindByFreePostIdAndPageDto(freePostId, pageable);

        var response = FreePostCommentConverter.toFreePostCommentFindByFreePostIdAndPageResponse(
                dto,
                totalCount,
                commentPage
        );

        // mocking
        Mockito.when(freePostCommentFacade.findByFreePostIdAndPage(anyLong(), any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/free-posts/{freePostId}/free-post-comments", freePostId))
                .andExpect(status().isOk());
    }

    @Test
    public void 자유게시글_댓글_수정하기() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(FreePostComment.class);
        var request = new FreePostCommentRequest("심심해유심심해");
        var response = new FreePostCommentResponse(comment.getId());

        // mocking
        Mockito.when(freePostCommentFacade.update(anyLong(), any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(
                        put("/api/v1/free-post-comments/{commentId}", comment.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 자유게시글_댓글_삭제하기() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(FreePostComment.class);
        var response = new FreePostCommentResponse(comment.getId());

        // mocking
        Mockito.when(freePostCommentFacade.delete(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(patch("/api/v1/free-post-comments/{commentId}", comment.getId()))
                .andExpect(status().isOk());
    }
}
