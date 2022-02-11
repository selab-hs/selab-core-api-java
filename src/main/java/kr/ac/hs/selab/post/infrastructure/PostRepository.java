package kr.ac.hs.selab.post.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsById(Long id);

    Optional<Post> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    List<Post> findByBoard(Board board);

    @Modifying
    @Query("update Post p set p.deleteFlag = :deleteFlag where p.board = :board")
    void deleteByBoard(@Param("board") Board board, @Param("deleteFlag") boolean deleteFlag);
}
