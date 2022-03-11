package kr.ac.hs.selab.post.infrastructure;

import kr.ac.hs.selab.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsById(Long id);

    Optional<Post> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    List<Post> findByBoardIdAndDeleteFlag(Long boardId, boolean deleteFlag);

    Page<Post> findByBoardIdAndDeleteFlag(Long boardId, boolean deleteFlag, Pageable pageable);

    Long countByBoardIdAndDeleteFlag(Long boardId, boolean deleteFlag);
}
