package kr.ac.hs.selab.notice.infrastructure;

import kr.ac.hs.selab.notice.domain.NoticeComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> {
    Optional<NoticeComment> findByIdAndDeleteFlag(Long id, boolean deleteFlag);

    List<NoticeComment> findByNoticeIdAndDeleteFlag(Long noticeId, boolean deleteFlag);

    Page<NoticeComment> findByNoticeIdAndDeleteFlag(Long noticeId, boolean deleteFlag, Pageable pageable);

    Long countByNoticeIdAndDeleteFlag(Long noticeId, boolean deleteFlag);
}
