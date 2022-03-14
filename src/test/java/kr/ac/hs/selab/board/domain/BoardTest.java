package kr.ac.hs.selab.board.domain;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    private Board board;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @BeforeEach
    public void setUp() {
        board = fixtureMonkey.giveMeBuilder(Board.class)
                .set("deleteFlag", false)
                .sample();
    }

    @Test
    public void 게시판_수정하기() {
        // when
        var newTitle = fixtureMonkey.giveMeOne(String.class);
        var newDescription = fixtureMonkey.giveMeOne(String.class);
        board.update(newTitle, newDescription);

        // then
        assertEquals(newTitle, board.getTitle());
        assertEquals(newDescription, board.getDescription());
    }

    @Test
    public void 게시판_소프트_삭제하기() {
        // given
        var oldTitle = board.getTitle();

        // when
        board.delete();

        // then
        assertEquals(oldTitle + "-" + board.getId(), board.getTitle());
        assertTrue(board.isDeleteFlag());
    }
}