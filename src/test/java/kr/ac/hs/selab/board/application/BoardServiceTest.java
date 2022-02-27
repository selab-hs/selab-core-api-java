package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.infrastructure.BoardRepository;
import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    private static final String TITLE = "자유 게시판";
    private static final String DESCRIPTION = "자유로운 게시판입니다.";

    @After
    public void tearDown() {
        boardRepository.deleteAll();
    }

    @Test
    public void 게시판_생성하기() {
        // given
        BoardCreateDto dto = new BoardCreateDto(TITLE, DESCRIPTION);

        // when
        Board actual = boardService.create(dto);
        Board expected = Board.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .build();

        // then
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void 아이디로_게시판_찾기() {
        // given
        Board board = boardService.create(
                new BoardCreateDto(TITLE, DESCRIPTION)
        );

        // when
        Board actual = boardService.findById(board.getId());
        Board expected = Board.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .build();

        // then
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test(expected = NonExitsException.class)
    public void 존재하지_않은_게시판을_찾을_경우() {
        // when, then
        boardService.findById(1L);
    }

    @Test
    public void 게시판_전체_리스트_가져오기() {
        // given
        IntStream.range(1, 11)
                .forEach(i -> boardService.create(
                                new BoardCreateDto(i + "번째 게시판", i + "번째 게시판 설명입니다.")
                        )
                );

        // when
        List<Board> actual = boardService.findAll();

        // when
        assertEquals(10, actual.size());
    }

    @Test
    public void 게시판_정보_수정하기() {
        // given
        Board board = boardService.create(
                new BoardCreateDto(TITLE, DESCRIPTION)
        );

        // when
        Board actual = boardService.update(
                BoardUpdateDto.builder()
                        .id(board.getId())
                        .title("장터 게시판")
                        .description("장터 게시판입니다.")
                        .build()
        );

        // then
        assertEquals("장터 게시판", actual.getTitle());
        assertEquals("장터 게시판입니다.", actual.getDescription());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void 이미_존재하는_제목의_게시판으로_수정할_경우() {
        // given
        boardService.create(
                new BoardCreateDto("자유 게시판", "자유로운 게시판입니다.")
        );
        Board board = boardService.create(
                new BoardCreateDto("장터 게시판", "장터 게시판입니다.")
        );

        // when, then
        boardService.update(
                BoardUpdateDto.builder()
                        .id(board.getId())
                        .title("자유 게시판")
                        .description("장터 게시판입니다.")
                        .build()
        );
    }

    @Test
    public void 게시판_삭제하기() {
        // given
        Board board = boardService.create(
                new BoardCreateDto("자유 게시판", "자유로운 게시판입니다.")
        );

        // when
        Board actual = boardService.delete(board.getId());

        // then
        assertEquals(Constants.DELETED, actual.isDeleteFlag());
    }
}
