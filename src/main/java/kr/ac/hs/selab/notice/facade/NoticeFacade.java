package kr.ac.hs.selab.notice.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.notice.application.NoticeService;
import kr.ac.hs.selab.notice.converter.NoticeConverter;
import kr.ac.hs.selab.notice.dto.NoticeUpdateDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindAllByPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeFindResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class NoticeFacade {
    private final NoticeService noticeService;
    private final MemberService memberService;

    @Transactional
    public NoticeResponse create(String memberEmail, NoticeRequest request) {
        var memberId = memberService.findByEmail(memberEmail).getId();

        var dto = NoticeConverter.toNoticeCreateDto(memberId, request);
        var notice = noticeService.create(dto);

        return new NoticeResponse(notice.getId());
    }

    @Transactional
    public NoticeFindResponse findNoticeResponseById(Long id) {
        var notice = noticeService.findById(id);
        return NoticeConverter.toNoticeFindResponse(notice);
    }

    public NoticeFindAllByPageResponse findNoticeFindAllByPageResponse(Pageable pageable) {
        var totalCount = noticeService.count();
        var notices = noticeService.findAllByPage(pageable);
        return NoticeConverter.toNoticeFindAllByPageResponse(totalCount, pageable, notices);
    }

    @Transactional
    public NoticeResponse update(Long noticeId, NoticeRequest request) {
        var dto = NoticeConverter.toNoticeUpdateDto(noticeId, request);
        var notice = noticeService.update(dto);
        return new NoticeResponse(notice.getId());
    }

    @Transactional
    public NoticeResponse delete(Long id) {
        var notice = noticeService.delete(id);
        return new NoticeResponse(notice.getId());
    }
}