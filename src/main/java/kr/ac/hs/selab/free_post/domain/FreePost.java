package kr.ac.hs.selab.free_post.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class FreePost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_post_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "free_post_title", nullable = false)
    private String title;

    @Column(name = "free_post_content", nullable = false)
    private String content;

    @Column(name = "free_post_delete_flag", nullable = false)
    private boolean deleteFlag;

    @Transient
    private static final String HYPHEN = "-";

    @Builder
    private FreePost(Long memberId, String title, String content) {
        this.title = title;
        this.memberId = memberId;
        this.content = content;
        this.deleteFlag = false;
    }

    public FreePost update(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }

    public FreePost delete() {
        this.deleteFlag = true;
        return this;
    }
}
