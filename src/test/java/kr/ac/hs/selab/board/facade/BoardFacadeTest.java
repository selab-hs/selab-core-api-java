package kr.ac.hs.selab.board.facade;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.event.BoardEvent;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.response.BoardFindAllResponse;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class BoardFacadeTest {

    @Mock
    private BoardService boardService;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private BoardFacade boardFacade;

    private static final Long ID = 1L;
    private static final BoardCreateDto CREATE_DTO = new BoardCreateDto("자유게시판", "자유게시판입니다.");

    @Test
    public void 게시판_생성_성공() {
        // given
        var board = BoardConverter.toBoard(CREATE_DTO);
        ReflectionTestUtils.setField(board, "id", ID);

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);

        // when
        var actual = boardFacade.create(CREATE_DTO);

        // then
        assertEquals(ID, actual.getId());
    }

    @Test
    public void 아이디로_게시판_찾기_성공() {
        // given
        var board = BoardConverter.toBoard(CREATE_DTO);
        ReflectionTestUtils.setField(board, "id", ID);
        var response = BoardConverter.toBoardFindResponse(board);

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);
        Mockito.when(boardService.findById(anyLong()))
                .thenReturn(board);

        // when
        var findBoard = boardFacade.create(CREATE_DTO);
        var actual = boardFacade.findBoardResponseById(findBoard.getId());

        // then
        assertEquals(response.getTitle(), actual.getTitle());
    }

    @Test
    public void 존재하지_않은_게시판을_찾을_경우_아이디로_게시판_찾기_실패() {
        // given
        var board = BoardConverter.toBoard(CREATE_DTO);
        ReflectionTestUtils.setField(board, "id", ID);

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);
        Mockito.when(boardService.findById(anyLong()))
                .thenThrow(NonExitsException.class);

        // when, then
        var createBoard = boardFacade.create(CREATE_DTO);
        assertThrows(NonExitsException.class, () -> boardFacade.findBoardResponseById(createBoard.getId()));
    }

    @Test
    public void 전체_게시판_찾기_성공() {
        // given
        List<BoardCreateDto> createDtos = new ArrayList<>();
        IntStream.range(0, 10)
                .forEach(dto -> createDtos.add(new BoardCreateDto(dto + "번 게시판", dto + "번 게시판설명입니다.")));

        List<Board> boards = createDtos.stream()
                .map(BoardConverter::toBoard)
                .collect(Collectors.toList());

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(boards.get(0));
        Mockito.when(boardService.findAll())
                .thenReturn(boards);

        // when
        IntStream.range(0, 10)
                .forEach(i -> boardFacade.create(createDtos.get(i)));
        BoardFindAllResponse actual = boardFacade.findBoardFindAllResponse();

        // when
        assertEquals(boards.size(), actual.getTotalCount());
        IntStream.range(0, 10)
                .forEach(i -> assertEquals(boards.get(i).getTitle(), actual.getBoards().get(i).getTitle()));
    }

    @Test
    public void 게시판_수정하기_성공() {
        // given
        var updateDto = BoardUpdateDto.builder()
                .id(ID)
                .title("장터게시판")
                .description("장터게시판입니다.")
                .build();

        var board = BoardConverter.toBoard(CREATE_DTO);
        ReflectionTestUtils.setField(board, "id", ID);

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);
        Mockito.when(boardService.update(any()))
                .thenReturn(board);

        // when
        boardFacade.create(CREATE_DTO);
        var actual = boardFacade.update(updateDto);

        // then
        assertEquals(ID, actual.getId());
    }

    @Test
    public void 게시판_삭제하기_성공() {
        // given
        var board = BoardConverter.toBoard(CREATE_DTO);
        ReflectionTestUtils.setField(board, "id", ID);

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);
        Mockito.when(boardService.delete(anyLong()))
                .thenReturn(board);
        Mockito.doNothing().when(publisher)
                .publishEvent(BoardEvent.of(board));

        // when
        boardFacade.create(CREATE_DTO);
        var actual = boardFacade.delete(ID);

        // then
        assertEquals(ID, actual.getId());
    }
}
