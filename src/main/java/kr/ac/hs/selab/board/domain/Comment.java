package kr.ac.hs.selab.board.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "comment_content", nullable = false)
    private String content;

    @Column(name = "comment_delete_flag", nullable = false)
    private boolean deleteFlag;

    @Builder
    public Comment(Long memberId, Long postId, String content) {
        this.memberId = memberId;
        this.postId = postId;
        this.content = content;
        this.deleteFlag = false;
    }

    public Comment update(String content) {
        this.content = content;
        return this;
    }

    public Comment delete() {
        this.deleteFlag = true;
        return this;
    }
}