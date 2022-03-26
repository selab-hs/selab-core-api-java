package kr.ac.hs.selab.free_post.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.free_post.converter.FreePostConverter;
import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post.dto.FreePostFindByPageDto;
import kr.ac.hs.selab.free_post.dto.request.FreePostRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostResponse;
import kr.ac.hs.selab.free_post.facade.FreePostFacade;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.notice.converter.NoticeConverter;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeFindAllByPageDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeResponse;
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
@Import(FreePostController.class)
@ExtendWith(MockitoExtension.class)
public class FreePostControllerTest {
    @MockBean
    private FreePostFacade freePostFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 자유게시글_생성하기() throws Exception {  // TODO Non exist member
//        // given
//        var member = fixtureMonkey.giveMeOne(Member.class);
//        var post = fixtureMonkey.giveMeBuilder(FreePost.class)
//                .set("title", "자유게시판의 제목입니다.")
//                .set("content", "자유롭게 글을 작성하시면 됩니다.")
//                .set("memberId", member.getId())
//                .sample();
//        var request = new FreePostRequest(
//                post.getTitle(),
//                post.getContent()
//        );
//        var response = new FreePostResponse(post.getId());
//
//        // mocking
//        Mockito.when(SecurityUtils.getCurrentUsername())
//                .thenReturn(member.getEmail());
//        Mockito.when(freePostFacade.create(anyString(), any()))
//                .thenReturn(response);
//
//        // when, then
//        mockMvc.perform(
//                        post("/api/v1/free-posts")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .characterEncoding("utf-8")
//                                .content(objectMapper.writeValueAsString(request))
//                )
//                .andExpect(status().isOk());
    }

    @Test
    public void 아이디로_자유게시글_조회하기() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var response = FreePostConverter.toFreePostFindByIdResponse(post);

        // mocking
        Mockito.when(freePostFacade.findById(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/free-posts/{id}", post.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 자유게시글_페이지로_조회하기() throws Exception {
        // given
        var totalCount = 100L;
        var pageable = PageRequest.of(1, 20);
        var posts = fixtureMonkey.giveMe(FreePost.class, (int) totalCount);

        var dto = FreePostFindByPageDto.builder()
                .totalCount(totalCount)
                .pageable(pageable)
                .freePosts(new PageImpl<>(posts, pageable, totalCount))
                .build();
        var response = FreePostConverter.toFreePostFindByPageResponse(dto);

        // mocking
        Mockito.when(freePostFacade.findByPage(any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(get("/api/v1/free-posts"))
                .andExpect(status().isOk());
    }

    @Test
    public void 자유게시글_수정하기() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var request = new FreePostRequest(
                "공지사항 수정이 있겠습니다.",
                "수정 내용은 아래와 같습니다."
        );
        var response = new FreePostResponse(post.getId());

        // mocking
        Mockito.when(freePostFacade.update(anyLong(), any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(
                        put("/api/v1/free-posts/{id}", post.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 자유게시글_삭제하기() throws Exception {
        // given
        var post = fixtureMonkey.giveMeOne(FreePost.class);
        var response = new FreePostResponse(post.getId());

        // mocking
        Mockito.when(freePostFacade.delete(anyLong()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(patch("/api/v1/free-posts/{id}", post.getId()))
                .andExpect(status().isOk());
    }
}
