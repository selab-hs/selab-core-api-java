package kr.ac.hs.selab.notice.infrastructure;

import kr.ac.hs.selab.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Long countByDeleteFlag(boolean deleteFlag);

    Page<Notice> findByDeleteFlag(boolean deleteFlag, Pageable pageable);

    Optional<Notice> findByIdAndDeleteFlag(Long id, boolean deleteFlag);
}