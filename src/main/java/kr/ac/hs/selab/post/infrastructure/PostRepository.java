package kr.ac.hs.selab.post.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsById(Long id);

    Optional<Post> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    List<Post> findByBoardAndDeleteFlag(Board board, boolean deleteFlag);

    Long countByBoardAndDeleteFlag(Board board, boolean deleteFlag);
}
