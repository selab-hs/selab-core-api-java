package kr.ac.hs.selab.free_post_comment.domain;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FreePostCommentTest {
    private FreePostComment noticeComment;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @BeforeEach
    void setUp() {
        noticeComment = fixtureMonkey.giveMeBuilder(FreePostComment.class)
                .set("deleteFlag", false)
                .sample();
    }

    @Test
    void 수정하기() {
        // when
        var newContent = fixtureMonkey.giveMeOne(String.class);
        noticeComment.update(newContent);

        // then
        assertEquals(newContent, noticeComment.getContent());
    }

    @Test
    void 삭제하기() {
        // when
        noticeComment.delete();

        // then
        assertTrue(noticeComment.isDeleteFlag());
    }
}
