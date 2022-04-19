package kr.ac.hs.selab.board.infrastructure;

import kr.ac.hs.selab.board.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    List<CommentLike> findByCommentId(Long commentId);
}
