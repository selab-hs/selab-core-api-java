package kr.ac.hs.selab.notice_like.application;

import kr.ac.hs.selab.notice_like.domain.NoticeLike;
import kr.ac.hs.selab.notice_like.dto.NoticeLikeDto;
import kr.ac.hs.selab.notice_like.infrastructure.NoticeLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoticeLikeService {
    private final NoticeLikeRepository noticeLikeRepository;

    @Transactional
    public NoticeLike create(NoticeLikeDto dto) {
        return noticeLikeRepository.save(
                new NoticeLike(dto.getMemberId(), dto.getNoticeId())
        );
    }

    public List<NoticeLike> find(Long noticeId) {
        return noticeLikeRepository.findByNoticeId(noticeId);
    }

    @Transactional
    public void deleteByNoticeLikeId(Long noticeLikeId) {
        noticeLikeRepository.deleteById(noticeLikeId);
    }

    @Transactional
    public void deleteByNoticeId(Long noticeId) {
        noticeLikeRepository.deleteAll(noticeLikeRepository.findByNoticeId(noticeId));
    }
}
