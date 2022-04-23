package kr.ac.hs.selab.free_post.domain;

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
public class FreePostComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_post_comment_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "free_post_id", nullable = false)
    private Long freePostId;

    @Column(name = "free_post_comment_content", nullable = false)
    private String content;

    @Column(name = "free_post_comment_delete_flag", nullable = false)
    private boolean deleteFlag;

    @Builder
    public FreePostComment(Long memberId, Long freePostId, String content) {
        this.memberId = memberId;
        this.freePostId = freePostId;
        this.content = content;
        this.deleteFlag = false;
    }

    public FreePostComment update(String content) {
        this.content = content;
        return this;
    }

    public FreePostComment delete() {
        this.deleteFlag = true;
        return this;
    }
}