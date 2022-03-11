package kr.ac.hs.selab.board.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = Board.builder()
                .title("자유게시판")
                .description("자유롭게 작성할 수 있는 게시판입니다.")
                .build();
    }

    @Test
    public void 게시판_수정하기() {
        // when
        board.update("장터게시판", "물건을 사고 팔 수 있는 게시판입니다.");

        // then
        assertEquals("장터게시판", board.getTitle());
        assertEquals("물건을 사고 팔 수 있는 게시판입니다.", board.getDescription());
    }

    @Test
    public void 게시판_소프트_삭제하기() {
        // when
        board.delete();

        // then
        assertEquals("자유게시판-" + board.getId(), board.getTitle());
        assertTrue(board.isDeleteFlag());
    }
}