package kr.ac.hs.selab.notice.application;

import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.notice.converter.NoticeConverter;
import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeCreateDto;
import kr.ac.hs.selab.notice.dto.NoticeUpdateDto;
import kr.ac.hs.selab.notice.infrastructure.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public Notice create(NoticeCreateDto dto) {
        return noticeRepository.save(NoticeConverter.toNotice(dto));
    }

    public Long count() {
        return noticeRepository.countByDeleteFlag(Constants.NOT_DELETED);
    }

    public Notice findById(Long id) {
        return noticeRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.NOTICE_NOT_EXISTS_ERROR));
    }

    public Page<Notice> findAllByPage(Pageable pageable) {
        return noticeRepository.findByDeleteFlag(Constants.NOT_DELETED, pageable);
    }

    @Transactional
    public Notice update(NoticeUpdateDto dto) {
        return findById(dto.getId()).update(dto.getTitle(), dto.getContent());
    }

    @Transactional
    public Notice delete(Long id) {
        return findById(id).delete();
    }
}
