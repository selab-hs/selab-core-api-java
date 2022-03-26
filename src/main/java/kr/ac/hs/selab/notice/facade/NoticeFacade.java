package kr.ac.hs.selab.notice.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.notice.application.NoticeService;
import kr.ac.hs.selab.notice.converter.NoticeConverter;
import kr.ac.hs.selab.notice.dto.NoticeFindByPageDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindByPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeFindByIdResponse;
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
    public NoticeFindByIdResponse findById(Long id) {
        var notice = noticeService.findById(id);
        return NoticeConverter.toNoticeFindByIdResponse(notice);
    }

    public NoticeFindByPageResponse findByPage(Pageable pageable) {
        var dto = NoticeFindByPageDto.builder()
                .totalCount(noticeService.count())
                .pageable(pageable)
                .notices(noticeService.findByPage(pageable))
                .build();
        return NoticeConverter.toNoticeFindByPageResponse(dto);
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