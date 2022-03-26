package kr.ac.hs.selab.notice_comment.domain;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.comment.domain.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoticeCommentTest {
    private NoticeComment noticeComment;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @BeforeEach
    void setUp() {
        noticeComment = fixtureMonkey.giveMeBuilder(NoticeComment.class)
                .set("deleteFlag", false)
                .sample();
    }

    @Test
    void 수정_성공() {
        // when
        var newContent = fixtureMonkey.giveMeOne(String.class);
        noticeComment.update(newContent);

        // then
        assertEquals(newContent, noticeComment.getContent());
    }

    @Test
    void 삭제_성공() {
        // when
        noticeComment.delete();

        // then
        assertTrue(noticeComment.isDeleteFlag());
    }
}
