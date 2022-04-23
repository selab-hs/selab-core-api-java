package kr.ac.hs.selab.board.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @Column(name = "post_title", nullable = false)
    private String title;

    @Column(name = "post_content", nullable = false)
    private String content;

    @Column(name = "post_delete_flag", nullable = false)
    private boolean deleteFlag;

    @Builder
    private Post(Long memberId, Long boardId, String title, String content) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.deleteFlag = false;
    }

    public Post update(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }

    public Post delete() {
        this.deleteFlag = true;
        return this;
    }
}
