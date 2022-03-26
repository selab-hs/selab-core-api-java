package kr.ac.hs.selab.free_post.domain;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.notice.domain.Notice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FreePostTest {
    private Notice notice;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @BeforeEach
    public void setUp() {
        notice = fixtureMonkey.giveMeBuilder(Notice.class)
                .set("deleteFlag", false)
                .sample();
    }

    @Test
    public void 자유게시글_수정하기() {
        // when
        var newTitle = fixtureMonkey.giveMeOne(String.class);
        var newContent = fixtureMonkey.giveMeOne(String.class);
        notice.update(newTitle, newContent);

        // then
        assertEquals(newTitle, notice.getTitle());
        assertEquals(newContent, notice.getContent());
    }

    @Test
    public void 자유게시글_소프트_삭제하기() {
        // when
        notice.delete();

        // then
        assertTrue(notice.isDeleteFlag());
    }
}