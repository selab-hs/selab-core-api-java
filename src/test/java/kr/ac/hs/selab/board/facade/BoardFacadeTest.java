package kr.ac.hs.selab.board.facade;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.event.BoardEvent;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.response.BoardFindAllResponse;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class BoardFacadeTest {
    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardFacade boardFacade;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시판_생성_성공() {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardCreateDto = new BoardCreateDto(board.getTitle(), board.getDescription());
        var expected = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);

        // when
        var actual = boardFacade.create(boardCreateDto);

        // then
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void 아이디로_게시판_찾기_성공() {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardCreateDto = new BoardCreateDto(board.getTitle(), board.getDescription());
        var expected = BoardConverter.toBoardFindResponse(board);

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);
        Mockito.when(boardService.findById(anyLong()))
                .thenReturn(board);

        // when
        var boardResponse = boardFacade.create(boardCreateDto);
        var actual = boardFacade.findBoardResponseById(boardResponse.getId());

        // then
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    public void 존재하지_않은_게시판을_찾을_경우_아이디로_게시판_찾기_실패() {
        // given
        var id = fixtureMonkey.giveMeOne(Long.class);

        // mocking
        Mockito.when(boardService.findById(anyLong()))
                .thenThrow(NonExitsException.class);

        // when, then
        assertThrows(NonExitsException.class, () -> boardFacade.findBoardResponseById(id));
    }

    @Test
    public void 전체_게시판_찾기_성공() {
        // given
        var boards = fixtureMonkey.giveMe(Board.class, 10);
        var boardCreateDtos = boards.stream()
                .map(board -> new BoardCreateDto(board.getTitle(), board.getDescription()))
                .collect(Collectors.toList());
        var expected = BoardConverter.toBoardFindAllResponse(boards);

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(boards.get(0));
        Mockito.when(boardService.findAll())
                .thenReturn(boards);

        // when
        IntStream.range(0, 10)
                .forEach(i -> boardFacade.create(boardCreateDtos.get(i)));
        BoardFindAllResponse actual = boardFacade.findBoardFindAllResponse();

        // when
        assertEquals(expected.getTotalCount(), actual.getTotalCount());
        IntStream.range(0, 10)
                .forEach(i -> assertEquals(expected.getBoards().get(i).getTitle(), actual.getBoards().get(i).getTitle()));
    }

    @Test
    public void 게시판_수정하기_성공() {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardCreateDto = new BoardCreateDto(board.getTitle(), board.getDescription());

        var boardUpdateDto = BoardUpdateDto.builder()
                .id(board.getId())
                .title(fixtureMonkey.giveMeOne(String.class))
                .description(fixtureMonkey.giveMeOne(String.class))
                .build();

        var expected = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);
        Mockito.when(boardService.update(any()))
                .thenReturn(board);

        // when
        boardFacade.create(boardCreateDto);
        var actual = boardFacade.update(boardUpdateDto);

        // then
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void 게시판_삭제하기_성공() {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var boardCreateDto = new BoardCreateDto(board.getTitle(), board.getDescription());
        var expected = new BoardResponse(board.getId());

        // mocking
        Mockito.when(boardService.create(any()))
                .thenReturn(board);
        Mockito.when(boardService.delete(anyLong()))
                .thenReturn(board);
        Mockito.doNothing().when(publisher)
                .publishEvent(BoardEvent.of(board));

        // when
        var boardResponse = boardFacade.create(boardCreateDto);
        var actual = boardFacade.delete(boardResponse.getId());

        // then
        assertEquals(expected.getId(), actual.getId());
    }
}
