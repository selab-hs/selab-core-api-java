package kr.ac.hs.selab.commentLike.infrastructure;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.commentLike.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    List<CommentLike> findByComment(Comment comment);
}
