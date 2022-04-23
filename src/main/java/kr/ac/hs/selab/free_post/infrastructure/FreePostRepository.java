package kr.ac.hs.selab.free_post.infrastructure;

import kr.ac.hs.selab.free_post.domain.FreePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FreePostRepository extends JpaRepository<FreePost, Long> {
    Long countByDeleteFlag(boolean deleteFlag);

    Page<FreePost> findByDeleteFlag(boolean deleteFlag, Pageable pageable);

    Optional<FreePost> findByIdAndDeleteFlag(Long id, boolean deleteFlag);
}