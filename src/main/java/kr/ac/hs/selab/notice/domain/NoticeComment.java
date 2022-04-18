package kr.ac.hs.selab.notice.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class NoticeComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_comment_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "notice_id", nullable = false)
    private Long noticeId;

    @Column(name = "notice_comment_content", nullable = false)
    private String content;

    @Column(name = "notice_comment_delete_flag", nullable = false)
    private boolean deleteFlag;

    @Builder
    public NoticeComment(Long memberId, Long noticeId, String content) {
        this.memberId = memberId;
        this.noticeId = noticeId;
        this.content = content;
        this.deleteFlag = false;
    }

    public NoticeComment update(String content) {
        this.content = content;
        return this;
    }

    public NoticeComment delete() {
        this.deleteFlag = true;
        return this;
    }
}