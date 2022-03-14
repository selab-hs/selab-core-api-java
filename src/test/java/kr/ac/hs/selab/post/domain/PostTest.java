package kr.ac.hs.selab.post.domain;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostTest {
    private Post post;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @BeforeEach
    void setUp() {
        post = fixtureMonkey.giveMeBuilder(Post.class)
                .set("deleteFlag", false)
                .sample();
    }

    @Test
    void 수정_성공() {
        // when
        var newTitle = fixtureMonkey.giveMeOne(String.class);
        var newContent = fixtureMonkey.giveMeOne(String.class);
        post.update(newTitle, newContent);

        // then
        assertEquals(newTitle, post.getTitle());
        assertEquals(newContent, post.getContent());
    }

    @Test
    void 삭제_성공() {
        // when
        post.delete();

        // then
        assertTrue(post.isDeleteFlag());
    }
}
