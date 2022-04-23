package kr.ac.hs.selab.board.infrastructure;

import kr.ac.hs.selab.board.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    List<Comment> findByPostIdAndDeleteFlag(Long postId, boolean deleteFlag);

    Page<Comment> findByPostIdAndDeleteFlag(Long postId, boolean deleteFlag, Pageable pageable);

    Long countByPostIdAndDeleteFlag(Long postId, boolean deleteFlag);
}