package kr.ac.hs.selab.board.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.builder()
                .title("자유게시판")
                .description("자유롭게 작성할 수 있는 게시판입니다.")
                .build();
    }

    @Test
    void 수정_성공() {
        // when
        board.update("장터게시판", "물건을 사고 팔 수 있는 게시판입니다.");

        // then
        assertEquals("장터게시판", board.getTitle());
        assertEquals("물건을 사고 팔 수 있는 게시판입니다.", board.getDescription());
    }

    @Test
    void 삭제_성공() {
        // when
        board.delete();

        // then
        assertEquals("자유게시판-" + board.getId(), board.getTitle());
        assertTrue(board.isDeleteFlag());
    }
}
