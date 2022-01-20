package kr.ac.hs.selab.board.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.member.domain.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.deleteFlag = false")
    List<Board> findAllByDeletedNot();

    @Query("select b from Board b where b.deleteFlag = false and b.id = :id")
    Optional<Board> findByDeletedNot(Long id);

    boolean existsByTitle(String title);
}
