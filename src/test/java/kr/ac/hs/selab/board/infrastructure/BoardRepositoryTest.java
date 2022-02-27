package kr.ac.hs.selab.board.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.common.utils.Constants;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @After
    public void tearDown() {
        boardRepository.deleteAll();
    }

    @Test
    public void 삭제_플래그로_게시판_리스트_가져오기() {
        // given
        IntStream.range(1, 11)
                .forEach(i -> boardRepository.save(
                                Board.builder()
                                        .title(i + "번째 게시판")
                                        .description(i + "번째 게시판 설명입니다.")
                                        .build()
                        )
                );

        // when
        List<Board> boards = boardRepository.findByDeleteFlag(Constants.NOT_DELETED);

        // then
        assertEquals(10, boards.size());
    }

    @Test
    public void 아이로와_삭제_플래그로_게시판_하나_가져오기() {
        // given
        Board board1 = boardRepository.save(
                Board.builder()
                        .title("자유 게시판")
                        .description("자유 게시판입니다.")
                        .build()
        );

        // when
        Board board2 = boardRepository.findByIdAndDeleteFlag(board1.getId(), Constants.NOT_DELETED)
                .orElseThrow(RuntimeException::new);

        // then
        assertEquals(board1.getTitle(), board2.getTitle());
        assertEquals(board1.getDescription(), board2.getDescription());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void 이미_존재하는_제목의_게시판을_생성할_경우() {
        // given
        boardRepository.save(
                Board.builder()
                        .title("자유 게시판")
                        .description("자유 게시판입니다.")
                        .build()
        );

        // when, then
        boardRepository.save(
                Board.builder()
                        .title("자유 게시판")
                        .description("내용은 서로 다릅니다.")
                        .build()
        );
    }
}
