package kr.ac.hs.selab.post.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.deleteFlag = :deleteFlag and p.id = :id")
    Optional<Post> find(@Param("id") Long id, @Param("deleteFlag") boolean deleteFlag);

    @Query("select p from Post p where p.board = :board")
    List<Post> findByBoard(@Param("board") Board board);
}
