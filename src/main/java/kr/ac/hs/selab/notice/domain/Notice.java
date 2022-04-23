package kr.ac.hs.selab.notice.domain;

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
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "notice_title", nullable = false)
    private String title;

    @Column(name = "notice_content", nullable = false)
    private String content;

    @Column(name = "notice_delete_flag", nullable = false)
    private boolean deleteFlag;

    @Builder
    private Notice(Long memberId, String title, String content) {
        this.title = title;
        this.memberId = memberId;
        this.content = content;
        this.deleteFlag = false;
    }

    public Notice update(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }

    public Notice delete() {
        this.deleteFlag = true;
        return this;
    }
}