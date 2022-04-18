package kr.ac.hs.selab.board.domain;

import kr.ac.hs.selab.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(
        name = "comment_like",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"member_id", "comment_id"}
                )
        }
)
public class CommentLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    public CommentLike(Long memberId, Long commentId) {
        this.memberId = memberId;
        this.commentId = commentId;
    }
}
