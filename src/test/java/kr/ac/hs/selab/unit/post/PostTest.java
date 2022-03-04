package kr.ac.hs.selab.unit.post;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.post.domain.Post;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class PostTest {
    private Post post;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .email("leeheefull@gmail.com")
                .password(new Password("zxcv1234!"))
                .studentId("이희찬")
                .nickname("leeheefull")
                .avatar(null)
                .build();

        Board board = Board.builder()
                .title("자유게시판")
                .description("자유롭게 작성할 수 있는 게시판입니다.")
                .build();

        post = Post.builder()
                .member(member)
                .board(board)
                .title("심심해용")
                .content("심심합니다")
                .build();
    }

    @Test
    void 수정_성공() {
        // when
        post.update("재밌어용", "재밌습니다.");

        // then
        assertEquals("재밌어용", post.getTitle());
        assertEquals("재밌습니다.", post.getContent());
    }

    @Test
    void 삭제_성공() {
        // when
        post.delete();

        // then
        assertTrue(post.isDeleteFlag());
    }
}
