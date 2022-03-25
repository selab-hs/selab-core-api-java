package kr.ac.hs.selab.notice_like.infrastructure;

import kr.ac.hs.selab.notice_like.domain.NoticeLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeLikeRepository extends JpaRepository<NoticeLike, Long> {
    List<NoticeLike> findByNoticeId(Long noticeId);
}
