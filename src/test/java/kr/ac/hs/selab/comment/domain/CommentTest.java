package kr.ac.hs.selab.comment.domain;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommentTest {
    private Comment comment;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @BeforeEach
    void setUp() {
        comment = fixtureMonkey.giveMeBuilder(Comment.class)
                .set("deleteFlag", false)
                .sample();
    }

    @Test
    void 수정_성공() {
        // when
        var newContent = fixtureMonkey.giveMeOne(String.class);
        comment.update(newContent);

        // then
        assertEquals(newContent, comment.getContent());
    }

    @Test
    void 삭제_성공() {
        // when
        comment.delete();

        // then
        assertTrue(comment.isDeleteFlag());
    }
}
