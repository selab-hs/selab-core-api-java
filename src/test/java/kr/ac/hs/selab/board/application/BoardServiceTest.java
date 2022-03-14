package kr.ac.hs.selab.board.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 게시판_생성_성공() {
        // given
        var expected = fixtureMonkey.giveMeOne(Board.class);
        var boardCreateDto = new BoardCreateDto(expected.getTitle(), expected.getDescription());

        // mocking
        Mockito.when(boardRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = boardService.create(boardCreateDto);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 아이디로_게시판_찾기_성공() {
        // given
        var expected = fixtureMonkey.giveMeOne(Board.class);

        // mocking
        Mockito.when(boardRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = boardService.findById(expected.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 존재하지_않은_게시판을_찾을_경우_아이디로_게시판_찾기_실패() {
        // given
        var id = fixtureMonkey.giveMeOne(Long.class);

        // mocking
        Mockito.when(boardRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenThrow(NonExitsException.class);

        // when, then
        assertThrows(NonExitsException.class, () -> boardService.findById(id));
    }

    @Test
    public void 전체_게시판_찾기_성공() {
        // given
        var expected = fixtureMonkey.giveMe(Board.class, 10);

        // mocking
        Mockito.when(boardRepository.findByDeleteFlag(anyBoolean()))
                .thenReturn(expected);

        // when
        var actual = boardService.findAll();

        // when
        IntStream.range(0, 10)
                .forEach(i -> assertEquals(expected.get(i), actual.get(i)));
    }

    @Test
    public void 게시판_수정하기_성공() {
        // given
        var board = fixtureMonkey.giveMeOne(Board.class);
        var expectedTitle = fixtureMonkey.giveMeOne(String.class);
        var expectedDescription = fixtureMonkey.giveMeOne(String.class);
        var boardUpdateDto = BoardUpdateDto.builder()
                .id(board.getId())
                .title(expectedTitle)
                .description(expectedDescription)
                .build();

        // mocking
        Mockito.when(boardRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(board));

        // when
        var actual = boardService.update(boardUpdateDto);

        // then
        assertEquals(expectedTitle, actual.getTitle());
        assertEquals(expectedDescription, actual.getDescription());
    }

    @Test
    public void 게시판_삭제하기_성공() {
        // given
        var expected = fixtureMonkey.giveMeBuilder(Board.class)
                .set("deleteFlag", false)
                .sample();

        // mocking
        Mockito.when(boardRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = boardService.delete(expected.getId());

        // then
        assertTrue(actual.isDeleteFlag());
    }
}