package kr.ac.hs.selab.notice.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(
        name = "notice_like",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"member_id", "notice_id"}
                )
        }
)
public class NoticeLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_like_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "notice_id", nullable = false)
    private Long noticeId;

    public NoticeLike(Long memberId, Long noticeId) {
        this.memberId = memberId;
        this.noticeId = noticeId;
    }
}