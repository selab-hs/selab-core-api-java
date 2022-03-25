package kr.ac.hs.selab.notice.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.notice.converter.NoticeConverter;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeFindAllByPageDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeResponse;
import kr.ac.hs.selab.notice.facade.NoticeFacade;
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
@Import(NoticeController.class)
@ExtendWith(MockitoExtension.class)
public class NoticeControllerTest {
    @MockBean
    private NoticeFacade noticeFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 공지사항_생성하기() throws Exception {  // TODO Non exist member
//        // given
//        var member = fixtureMonkey.giveMeOne(Member.class);
//        var notice = fixtureMonkey.giveMeBuilder(Notice.class)
//                .set("title", "환영합니다. 홈페이지가 개설됐습니다.")
//                .set("content", "소프트웨어 엔지니어 연구실 연구원분들을 위한 홈페이지입니다.")
//                .set("memberId", member.getId())
//                .sample();
//        var noticeRequest = new NoticeRequest(
//                notice.getTitle(),
//                notice.getContent(),
//                notice.getImage()
//        );
//        var noticeResponse = new NoticeResponse(notice.getId());
//
//        // mocking
//        Mockito.when(SecurityUtils.getCurrentUsername())
//                .thenReturn(member.getEmail());
//        Mockito.when(noticeFacade.create(anyString(), any()))
//                .thenReturn(noticeResponse);
//
//        // when, then
//        mockMvc.perform(
//                        post("/api/v1/notices")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .characterEncoding("utf-8")
//                                .content(objectMapper.writeValueAsString(noticeRequest))
//                )
//                .andExpect(status().isOk());
    }

    @Test
    public void 아이디로_공지사항_조회하기() throws Exception {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var noticeFindResponse = NoticeConverter.toNoticeFindResponse(notice);

        // mocking
        Mockito.when(noticeFacade.findNoticeResponseById(anyLong()))
                .thenReturn(noticeFindResponse);

        // when, then
        mockMvc.perform(get("/api/v1/notices/{id}", notice.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 공지사항_전체_페이지로_조회하기() throws Exception {
        // given
        var totalCount = 100L;
        var pageable = PageRequest.of(1, 20);
        var notices = fixtureMonkey.giveMe(Notice.class, (int) totalCount);

        var noticeFindAllByPageDto = NoticeFindAllByPageDto.builder()
                .totalCount(totalCount)
                .pageable(pageable)
                .notices(new PageImpl<>(notices, pageable, totalCount))
                .build();
        var noticeFindAllResponse = NoticeConverter.toNoticeFindAllByPageResponse(noticeFindAllByPageDto);

        // mocking
        Mockito.when(noticeFacade.findNoticeFindAllByPageResponse(any()))
                .thenReturn(noticeFindAllResponse);

        // when, then
        mockMvc.perform(get("/api/v1/notices"))
                .andExpect(status().isOk());
    }

    @Test
    public void 공지사항_수정하기() throws Exception {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var noticeRequest = new NoticeRequest(
                "공지사항 수정이 있겠습니다.",
                "수정 내용은 아래와 같습니다.",
                null
        );
        var noticeResponse = new NoticeResponse(notice.getId());

        // mocking
        Mockito.when(noticeFacade.update(anyLong(), any()))
                .thenReturn(noticeResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/notices/{id}", notice.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(noticeRequest))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 공지사항_삭제하기() throws Exception {
        // given
        var notice = fixtureMonkey.giveMeOne(Notice.class);
        var noticeResponse = new NoticeResponse(notice.getId());

        // mocking
        Mockito.when(noticeFacade.delete(anyLong()))
                .thenReturn(noticeResponse);

        // when, then
        mockMvc.perform(patch("/api/v1/notices/{id}", notice.getId()))
                .andExpect(status().isOk());
    }
}
