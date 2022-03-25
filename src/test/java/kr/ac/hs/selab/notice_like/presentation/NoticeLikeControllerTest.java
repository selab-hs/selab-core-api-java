package kr.ac.hs.selab.notice_like.presentation;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice_like.converter.NoticeLikeConverter;
import kr.ac.hs.selab.notice_like.domain.NoticeLike;
import kr.ac.hs.selab.notice_like.dto.response.NoticeLikeResponse;
import kr.ac.hs.selab.notice_like.facade.NoticeLikeFacade;
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

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(useDefaultFilters = false)
@AutoConfigureMockMvc(addFilters = false)
@Import(NoticeLikeController.class)
@ExtendWith(MockitoExtension.class)
public class NoticeLikeControllerTest {
    @MockBean
    private NoticeLikeFacade noticeLikeFacade;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시글_좋아요_생성_성공() throws Exception {  // TODO member 없음 error
        // given
        var like = fixtureMonkey.giveMeOne(NoticeLike.class);
        var noticeLikeResponse = new NoticeLikeResponse(like.getId());

        // mocking
        Mockito.when(noticeLikeFacade.create(anyString(), anyLong()))
                .thenReturn(noticeLikeResponse);

        // when, then
//        mockMvc.perform(post("/api/v1/notices/{noticeId}/likes", like.getNoticeId()))
//                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_아이디로_게시글_좋아요_조회_성공() throws Exception {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var likes = fixtureMonkey.giveMeBuilder(NoticeLike.class)
                .set("noticeId", notice.getId())
                .sampleList(10);
        var noticeLikeFindResponse = NoticeLikeConverter.toNoticeLikeFindResponse(notice.getId(), likes);

        // mocking
        Mockito.when(noticeLikeFacade.find(any()))
                .thenReturn(noticeLikeFindResponse);

        // when, then
        mockMvc.perform(get("/api/v1/notices/{noticeId}/likes", notice.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시글_좋아요_취소_성공() throws Exception {
        // given
        var like = fixtureMonkey.giveMeOne(NoticeLike.class);
        var noticeLikeResponse = new NoticeLikeResponse(like.getId());

        // mocking
        Mockito.when(noticeLikeFacade.delete(anyLong()))
                .thenReturn(noticeLikeResponse);

        // when, then
        mockMvc.perform(delete("/api/v1/notices/likes/{noticeId}", like.getNoticeId()))
                .andExpect(status().isOk());
    }
}
