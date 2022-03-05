package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.converter.BoardConverter;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    private static final Long ID = 1L;
    private static final BoardCreateDto CREATE_DTO = new BoardCreateDto("자유게시판", "자유게시판입니다.");

    @Test
    public void 게시판_생성_성공() {
        // given
        var board = BoardConverter.toBoard(CREATE_DTO);

        // mocking
        Mockito.when(boardRepository.save(any()))
                .thenReturn(board);

        // when
        var actual = boardService.create(CREATE_DTO);

        // then
        assertEquals(board, actual);
    }

    @Test
    public void 아이디로_게시판_찾기_성공() {
        // given
        var board = BoardConverter.toBoard(CREATE_DTO);
        ReflectionTestUtils.setField(board, "id", ID);

        // mocking
        Mockito.when(boardRepository.save(any()))
                .thenReturn(board);
        Mockito.when(boardRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(board));

        // when
        var findBoard = boardService.create(CREATE_DTO);
        var actual = boardService.findById(findBoard.getId());

        // then
        assertEquals(board, actual);
    }

    @Test
    public void 존재하지_않은_게시판을_찾을_경우_아이디로_게시판_찾기_실패() {
        // given
        var board = BoardConverter.toBoard(CREATE_DTO);
        ReflectionTestUtils.setField(board, "id", ID);

        // mocking
        Mockito.when(boardRepository.save(any()))
                .thenReturn(board);
        Mockito.when(boardRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenThrow(NonExitsException.class);

        // when, then
        var createBoard = boardService.create(CREATE_DTO);
        assertThrows(NonExitsException.class, () -> boardService.findById(createBoard.getId()));
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
        Mockito.when(boardRepository.save(any()))
                .thenReturn(boards.get(0));
        Mockito.when(boardRepository.findByDeleteFlag(anyBoolean()))
                .thenReturn(boards);

        // when
        IntStream.range(0, 10)
                .forEach(i -> boardService.create(createDtos.get(i)));
        List<Board> actual = boardService.findAll();

        // when
        IntStream.range(0, 10)
                .forEach(i -> assertEquals(boards.get(i), actual.get(i)));
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
        Mockito.when(boardRepository.save(any()))
                .thenReturn(board);
        Mockito.when(boardRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(board));

        // when
        boardService.create(CREATE_DTO);
        var actual = boardService.update(updateDto);

        // then
        assertEquals("장터게시판", actual.getTitle());
        assertEquals("장터게시판입니다.", actual.getDescription());
    }

    @Test
    public void 게시판_삭제하기_성공() {
        // given
        var board = BoardConverter.toBoard(CREATE_DTO);
        ReflectionTestUtils.setField(board, "id", ID);

        // mocking
        Mockito.when(boardRepository.save(any()))
                .thenReturn(board);
        Mockito.when(boardRepository.findByIdAndDeleteFlag(anyLong(), anyBoolean()))
                .thenReturn(Optional.of(board));

        // when
        boardService.create(CREATE_DTO);
        var actual = boardService.delete(ID);

        // then
        assertTrue(actual.isDeleteFlag());
    }
}