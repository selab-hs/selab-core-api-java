package kr.ac.hs.selab.board.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.deleteFlag = :deleteFlag")
    List<Board> findAll(@Param("deleteFlag") Boolean deleteFlag);

    @Query("select b from Board b where b.deleteFlag = :deleteFlag and b.id = :id")
    Optional<Board> find(@Param("id") Long id, @Param("deleteFlag") Boolean deleteFlag);
}
