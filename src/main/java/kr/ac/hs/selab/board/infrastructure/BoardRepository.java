package kr.ac.hs.selab.board.infrastructure;

import kr.ac.hs.selab.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByDeleteFlag(boolean deleteFlag);

    Optional<Board> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    Long countByDeleteFlag(boolean deleteFlag);
}