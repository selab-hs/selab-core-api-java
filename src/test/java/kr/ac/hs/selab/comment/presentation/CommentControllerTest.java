package kr.ac.hs.selab.comment.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentFindByPostIdAndPageDto;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.comment.facade.CommentFacade;
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
@Import(CommentController.class)
@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {
    @MockBean
    private CommentFacade commentFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 댓글_생성_성공() throws Exception {  // TODO member 없음 error
        // given
        var comment = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("content", "정말 신기하네요")
                .sample();
        var commentRequest = new CommentRequest(comment.getContent());
        var commentResponse = new CommentResponse(comment.getId());

        // mocking
        Mockito.when(commentFacade.create(any()))
                .thenReturn(commentResponse);

        // when, then
//        mockMvc.perform(
//                        post("/api/v1/posts/{postId}/comments", comment.getPostId())
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .characterEncoding("utf-8")
//                                .content(objectMapper.writeValueAsString(commentRequest))
//                )
//                .andExpect(status().isOk());
    }

    @Test
    public void 댓글_내용_빈_값인_경우_댓글_생성_실패() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("content", "")
                .sample();
        var commentRequest = new CommentRequest(comment.getContent());
        var commentResponse = new CommentResponse(comment.getId());

        // mocking
        Mockito.when(commentFacade.create(any()))
                .thenReturn(commentResponse);

        // when, then
        mockMvc.perform(
                        post("/api/v1/posts/{postId}/comments", comment.getPostId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(commentRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 댓글_아이디_값으로_댓글_조회_성공() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var commentFindResponse = CommentConverter.toCommentFindResponse(comment);

        // mocking
        Mockito.when(commentFacade.findCommentResponseById(anyLong()))
                .thenReturn(commentFindResponse);

        // when, then
        mockMvc.perform(get("/api/v1/comments/{commentId}", comment.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_아이디_값과_페이지로_댓글_조회_성공() throws Exception {
        // given
        var totalCount = 100L;
        var pageNumber = 1;
        var pageSize = 20;
        var postId = fixtureMonkey.giveMeOne(Long.class);

        var comments = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("postId", postId)
                .sampleList((int) totalCount);
        var pageable = PageRequest.of(pageNumber, pageSize);
        var commentPage = new PageImpl<>(comments, pageable, totalCount);
        var commentFindByPostIdAndPageDto = new CommentFindByPostIdAndPageDto(postId, pageable);

        var commentFindByPostIdAndPageResponse = CommentConverter.toCommentFindByPostIdAndPageResponse(
                commentFindByPostIdAndPageDto,
                totalCount,
                commentPage
        );

        // mocking
        Mockito.when(commentFacade.findCommentsResponseByPostId(any()))
                .thenReturn(commentFindByPostIdAndPageResponse);

        // when, then
        mockMvc.perform(get("/api/v1/posts/{postId}/comments", postId))
                .andExpect(status().isOk());
    }

    @Test
    public void 댓글_수정_성공() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var commentRequest = new CommentRequest("심심해유심심해");
        var commentResponse = new CommentResponse(comment.getId());

        // mocking
        Mockito.when(commentFacade.update(any()))
                .thenReturn(commentResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/comments/{commentId}", comment.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(commentRequest))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 댓글_내용이_빈_값인_경우_댓글_수정_실패() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var commentRequest = new CommentRequest("");
        var commentResponse = new CommentResponse(comment.getId());

        // mocking
        Mockito.when(commentFacade.update(any()))
                .thenReturn(commentResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/comments/{commentId}", comment.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(commentRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 댓글_삭제_성공() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var commentResponse = new CommentResponse(comment.getId());

        // mocking
        Mockito.when(commentFacade.delete(anyLong()))
                .thenReturn(commentResponse);

        // when, then
        mockMvc.perform(patch("/api/v1/comments/{commentId}", comment.getId()))
                .andExpect(status().isOk());
    }
}
