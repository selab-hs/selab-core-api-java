package kr.ac.hs.selab.commentLike.domain;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.common.domain.BaseEntity;
import kr.ac.hs.selab.member.domain.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    public CommentLike(Member member, Comment comment) {
        this.member = member;
        this.comment = comment;
    }
}
