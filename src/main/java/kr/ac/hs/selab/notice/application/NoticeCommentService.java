package kr.ac.hs.selab.notice.application;

import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.converter.NoticeCommentConverter;
import kr.ac.hs.selab.notice.domain.NoticeComment;
import kr.ac.hs.selab.notice.dto.NoticeCommentCreateDto;
import kr.ac.hs.selab.notice.dto.NoticeCommentFindByNoticeIdAndPageDto;
import kr.ac.hs.selab.notice.dto.NoticeCommentUpdateDto;
import kr.ac.hs.selab.notice.infrastructure.NoticeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoticeCommentService {
    private final NoticeCommentRepository noticeCommentRepository;

    @Transactional
    public NoticeComment create(NoticeCommentCreateDto dto) {
        var comment = NoticeCommentConverter.toNoticeComment(dto);
        return noticeCommentRepository.save(comment);
    }

    public Long count(Long noticeId) {
        return noticeCommentRepository.countByNoticeIdAndDeleteFlag(noticeId, Constants.NOT_DELETED);
    }

    public NoticeComment findByNoticeCommentId(Long commentId) {
        return noticeCommentRepository.findByIdAndDeleteFlag(commentId, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.NOTICE_COMMENT_NOT_EXISTS_ERROR));
    }

    public List<NoticeComment> findByNoticeId(Long noticeId) {
        return noticeCommentRepository.findByNoticeIdAndDeleteFlag(noticeId, Constants.NOT_DELETED);
    }

    public Page<NoticeComment> findByNoticeIdAndPageDto(NoticeCommentFindByNoticeIdAndPageDto dto) {
        return noticeCommentRepository.findByNoticeIdAndDeleteFlag(
                dto.getNoticeId(),
                Constants.NOT_DELETED,
                dto.getPageable()
        );
    }

    @Transactional
    public NoticeComment update(NoticeCommentUpdateDto dto) {
        return findByNoticeCommentId(dto.getNoticeCommentId()).update(dto.getNoticeCommentContent());
    }

    @Transactional
    public NoticeComment deleteByNoticeCommentId(Long commentId) {
        return findByNoticeCommentId(commentId).delete();
    }

    @Transactional
    public void deleteByNoticeId(Long noticeId) {
        findByNoticeId(noticeId).forEach(NoticeComment::delete);
    }

    @Transactional
    public void deleteByNotices(List<Notice> notices) {
        notices.forEach(notice -> deleteByNoticeId(notice.getId()));
    }
}
