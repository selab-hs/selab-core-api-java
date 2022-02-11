package kr.ac.hs.selab.likes.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import kr.ac.hs.selab.likes.domain.vo.TargetType;
import kr.ac.hs.selab.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(
        name = "likes",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "member_id",
                                "likes_target_type",
                                "likes_target_id"
                        }
                )
        }
)
public class Likes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "likes_target_type")
    private TargetType targetType;

    @Column(name = "likes_target_id")
    private Long targetId;

    @Builder
    private Likes(Member member, TargetType targetType, Long targetId) {
        this.member = member;
        this.targetType = targetType;
        this.targetId = targetId;
    }
}
