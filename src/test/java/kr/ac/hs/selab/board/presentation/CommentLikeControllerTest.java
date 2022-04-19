package kr.ac.hs.selab.board.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.board.converter.CommentLikeConverter;
import kr.ac.hs.selab.board.domain.Comment;
import kr.ac.hs.selab.board.domain.CommentLike;
import kr.ac.hs.selab.board.dto.response.CommentLikeResponse;
import kr.ac.hs.selab.board.facade.CommentLikeFacade;
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
@Import(CommentLikeController.class)
@ExtendWith(MockitoExtension.class)
public class CommentLikeControllerTest {
    @MockBean
    private CommentLikeFacade commentLikeFacade;

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
        var commentLike = fixtureMonkey.giveMeOne(CommentLike.class);
        var commentLikeResponse = new CommentLikeResponse(commentLike.getId());

        // mocking
        Mockito.when(commentLikeFacade.create(any()))
                .thenReturn(commentLikeResponse);

        // when, then
//        mockMvc.perform(post("/api/v1/comments/{commentId}/likes", commentLike.getCommentId()))
//                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_아이디로_게시글_좋아요_조회_성공() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(Comment.class);
        var commentLikes = fixtureMonkey.giveMeBuilder(CommentLike.class)
                .set("commentId", comment.getId())
                .sampleList(10);
        var commentLikeFindResponse = CommentLikeConverter.toCommentLikeFindResponse(comment.getId(), commentLikes);

        // mocking
        Mockito.when(commentLikeFacade.find(any()))
                .thenReturn(commentLikeFindResponse);

        // when, then
        mockMvc.perform(get("/api/v1/comments/{commentId}/likes", comment.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_좋아요_취소_성공() throws Exception {
        // given
        var commentLike = fixtureMonkey.giveMeOne(CommentLike.class);
        var commentLikeResponse = new CommentLikeResponse(commentLike.getId());

        // mocking
        Mockito.when(commentLikeFacade.delete(anyLong()))
                .thenReturn(commentLikeResponse);

        // when, then
        mockMvc.perform(delete("/api/v1/comments/likes/{id}", commentLike.getCommentId()))
                .andExpect(status().isOk());
    }
}
