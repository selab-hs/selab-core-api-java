package kr.ac.hs.selab.notice_comment.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.notice_comment.converter.NoticeCommentConverter;
import kr.ac.hs.selab.notice_comment.domain.NoticeComment;
import kr.ac.hs.selab.notice_comment.dto.NoticeCommentFindByNoticeIdAndPageDto;
import kr.ac.hs.selab.notice_comment.dto.request.NoticeCommentRequest;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentResponse;
import kr.ac.hs.selab.notice_comment.facade.NoticeCommentFacade;
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

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(useDefaultFilters = false)
@AutoConfigureMockMvc(addFilters = false)
@Import(NoticeCommentController.class)
@ExtendWith(MockitoExtension.class)
public class NoticeCommentControllerTest {
    @MockBean
    private NoticeCommentFacade noticeCommentFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 공지사항_댓글_생성하기() throws Exception {  // TODO member 없음 error
        // given
        var comment = fixtureMonkey.giveMeOne(NoticeComment.class);
        var request = new NoticeCommentRequest(comment.getContent());
        var response = new NoticeCommentResponse(comment.getId());

        // mocking
        Mockito.when(noticeCommentFacade.create(anyLong(), anyString(), any()))
                .thenReturn(response);

        // when, then
//        mockMvc.perform(
//                        post("/api/v1/notices/{noticeId}/notice-comments", comment.getNoticeId())
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .characterEncoding("utf-8")
//                                .content(objectMapper.writeValueAsString(request))
//                )
//                .andExpect(status().isOk());
    }

    @Test
    public void 공지사항_댓글_아이디_값으로_댓글_조회하기() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(NoticeComment.class);
        var response = NoticeCommentConverter.toNoticeCommentFindResponse(comment);

        // mocking
        Mockito.when(noticeCommentFacade.findByNoticeCommentId(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/notice-comments/{commentId}", comment.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 공지사항_아이디_값과_페이지로_댓글_조회하기() throws Exception {
        // given
        var totalCount = 100L;
        var noticeId = fixtureMonkey.giveMeOne(Long.class);

        var comments = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("noticeId", noticeId)
                .sampleList((int) totalCount);
        var pageable = PageRequest.of(1, 20);
        var commentPage = new PageImpl<>(comments, pageable, totalCount);
        var dto = new NoticeCommentFindByNoticeIdAndPageDto(noticeId, pageable);

        var response = NoticeCommentConverter.toNoticeCommentFindByNoticeIdAndPageResponse(
                dto,
                totalCount,
                commentPage
        );

        // mocking
        Mockito.when(noticeCommentFacade.findByNoticeIdAndPage(anyLong(), any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/notices/{noticeId}/notice-comments", noticeId))
                .andExpect(status().isOk());
    }

    @Test
    public void 공지사항_댓글_수정하기() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(NoticeComment.class);
        var request = new NoticeCommentRequest("심심해유심심해");
        var response = new NoticeCommentResponse(comment.getId());

        // mocking
        Mockito.when(noticeCommentFacade.update(anyLong(), any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(
                        put("/api/v1/notice-comments/{commentId}", comment.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 공지사항_댓글_삭제하기() throws Exception {
        // given
        var comment = fixtureMonkey.giveMeOne(NoticeComment.class);
        var response = new NoticeCommentResponse(comment.getId());

        // mocking
        Mockito.when(noticeCommentFacade.delete(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(patch("/api/v1/notice-comments/{commentId}", comment.getId()))
                .andExpect(status().isOk());
    }
}
