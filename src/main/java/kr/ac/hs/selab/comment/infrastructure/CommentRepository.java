package kr.ac.hs.selab.comment.infrastructure;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    List<Comment> findByPost(Post post);

    @Modifying
    @Query("update Comment c set c.deleteFlag = :deleteFlag where c.post = :post")
    void deleteByPost(@Param("post") Post post, @Param("deleteFlag") boolean deleteFlag);
}
