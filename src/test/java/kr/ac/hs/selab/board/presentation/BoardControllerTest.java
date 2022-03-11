package kr.ac.hs.selab.board.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.request.BoardRequest;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.facade.BoardFacade;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(useDefaultFilters = false)
@AutoConfigureMockMvc(addFilters = false)
@Import(BoardController.class)
@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {
    @MockBean
    private BoardFacade boardFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시판_생성_성공() throws Exception {
        // given
        var board = fixtureMonkey.giveMeBuilder(Board.class)
                .set("title", "자유게시판")
                .set("description", "자유게시판입니다.")
                .sample();
        var boardRequest = new BoardRequest(board.getTitle(), board.getDescription());
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.create(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        post("/api/v1/admin/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 게시판_제목이_영어_이름일_경우_게시판_생성_실패() throws Exception {
        // given
        var board = fixtureMonkey.giveMeBuilder(Board.class)
                .set("title", "zxcv")
                .set("description", "자유게시판입니다.")
                .sample();
        var boardRequest = new BoardRequest(board.getTitle(), board.getDescription());
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.create(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        post("/api/v1/admin/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시판_제목이_2글자_미만일_경우_게시판_생성_실패() throws Exception {
        // given
        var board = fixtureMonkey.giveMeBuilder(Board.class)
                .set("title", "찬")
                .set("description", "자유게시판입니다.")
                .sample();
        var boardRequest = new BoardRequest(board.getTitle(), board.getDescription());
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.create(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        post("/api/v1/admin/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시판_제목이_10글자_초과일_경우_게시판_생성_실패() throws Exception {
        // given
        var board = fixtureMonkey.giveMeBuilder(Board.class)
                .set("title", "이희찬이희찬이희찬이희찬")
                .set("description", "자유게시판입니다.")
                .sample();
        var boardRequest = new BoardRequest(board.getTitle(), board.getDescription());
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.create(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        post("/api/v1/admin/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시판_설명이_30자_초과일_경우_게시판_생성_실패() throws Exception {
        // given
        var board = fixtureMonkey.giveMeBuilder(Board.class)
                .set("title", "자유게시판")
                .set("description", "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ")
                .sample();
        var boardRequest = new BoardRequest(board.getTitle(), board.getDescription());
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.create(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        post("/api/v1/admin/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 아이디로_게시판_조회_성공() throws Exception {
        // given
        var board = fixtureMonkey.giveMeBuilder(Board.class)
                .set("title", "자유게시판")
                .set("description", "자유게시판입니다.")
                .sample();
        var boardFindResponse = BoardConverter.toBoardFindResponse(board);

        // mocking
        Mockito.when(boardFacade.findBoardResponseById(anyLong()))
                .thenReturn(boardFindResponse);

        // when, then
        mockMvc.perform(get("/api/v1/admin/boards/{id}", board.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시판_전체_조회_성공() throws Exception {
        // given
        var boards = fixtureMonkey.giveMe(Board.class, 10);
        var boardFindAllResponse = BoardConverter.toBoardFindAllResponse(boards);

        // mocking
        Mockito.when(boardFacade.findBoardFindAllResponse())
                .thenReturn(boardFindAllResponse);

        // when, then
        mockMvc.perform(get("/api/v1/admin/boards"))
                .andExpect(status().isOk());
    }

    @Test
    public void 게시판_수정_성공() throws Exception {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardRequest = new BoardRequest("장터게시판", "장터게시판입니다.");
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.update(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/admin/boards/{id}", board.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void 게시판_제목이_영어일_경우_게시판_수정_실패() throws Exception {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardRequest = new BoardRequest("cks", "장터게시판입니다.");
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.update(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/admin/boards/{id}", board.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시판_제목이_2글자_미만일_경우_게시판_수정_실패() throws Exception {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardRequest = new BoardRequest("찬", "장터게시판입니다.");
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.update(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/admin/boards/{id}", board.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시판_제목이_10글자_초과일_경우_게시판_수정_실패() throws Exception {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardRequest = new BoardRequest("이희찬이희찬이희찬이희찬", "장터게시판입니다.");
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.update(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/admin/boards/{id}", board.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시판_설명이_30글자_초과일_경우_게시판_수정_실패() throws Exception {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardRequest = new BoardRequest("장터게시판", "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.update(any()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(
                        put("/api/v1/admin/boards/{id}", board.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(objectMapper.writeValueAsString(boardRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 게시판_삭제_성공() throws Exception {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardResponse = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardFacade.delete(anyLong()))
                .thenReturn(boardResponse);

        // when, then
        mockMvc.perform(patch("/api/v1/admin/boards/{id}", board.getId()))
                .andExpect(status().isOk());
    }
}
