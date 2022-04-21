package kr.ac.hs.selab.member.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.hs.selab.member.dto.request.MemberExistRequest;
import kr.ac.hs.selab.member.dto.response.MemberExistResponse;
import kr.ac.hs.selab.member.facade.MemberFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(useDefaultFilters = false)
@AutoConfigureMockMvc(addFilters = false)
@Import(MemberController.class)
@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {
    @MockBean
    private MemberFacade memberFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 회원가입_여부_조회() throws Exception {
        // given
        var request = new MemberExistRequest("leeheefull@gmail.com");
        var response = MemberExistResponse.of(true);

        // mocking
        Mockito.when(memberFacade.exist(any()))
                .thenReturn(response);

        // when, then
        mockMvc.perform(
                        post("/api/v1/members/exist")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }
}
