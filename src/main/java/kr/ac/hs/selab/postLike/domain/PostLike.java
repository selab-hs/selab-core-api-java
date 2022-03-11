package kr.ac.hs.selab.postLike.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(
        name = "post_like",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"member_id", "post_id"}
                )
        }
)
public class PostLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    public PostLike(Long memberId, Long postId) {
        this.memberId = memberId;
        this.postId = postId;
    }
}
