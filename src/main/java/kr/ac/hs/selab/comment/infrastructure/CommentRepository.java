package kr.ac.hs.selab.comment.infrastructure;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    List<Comment> findByPostAndDeleteFlag(Post post, boolean deleteFlag);

    Page<Comment> findByPostAndDeleteFlag(Post post, boolean deleteFlag, Pageable pageable);

    Long countByPostAndDeleteFlag(Post post, boolean deleteFlag);
}
