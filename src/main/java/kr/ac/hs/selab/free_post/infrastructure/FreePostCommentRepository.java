package kr.ac.hs.selab.free_post.infrastructure;

import kr.ac.hs.selab.free_post.domain.FreePostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FreePostCommentRepository extends JpaRepository<FreePostComment, Long> {
    Optional<FreePostComment> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    List<FreePostComment> findByFreePostIdAndDeleteFlag(Long freePostId, boolean deleteFlag);

    Page<FreePostComment> findByFreePostIdAndDeleteFlag(Long freePostId, boolean deleteFlag, Pageable pageable);

    Long countByFreePostIdAndDeleteFlag(Long freePostId, boolean deleteFlag);
}
