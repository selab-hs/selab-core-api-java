package kr.ac.hs.selab.notice.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.notice.application.NoticeCommentService;
import kr.ac.hs.selab.notice.converter.NoticeCommentConverter;
import kr.ac.hs.selab.notice.dto.NoticeCommentCreateDto;
import kr.ac.hs.selab.notice.dto.NoticeCommentFindByNoticeIdAndPageDto;
import kr.ac.hs.selab.notice.dto.NoticeCommentUpdateDto;
import kr.ac.hs.selab.notice.dto.request.NoticeCommentRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeCommentFindByNoticeIdAndPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeCommentFindResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class NoticeCommentFacade {
    private final MemberService memberService;
    private final NoticeCommentService noticeCommentService;

    @Transactional
    public NoticeCommentResponse create(Long noticeId, String memberEmail, NoticeCommentRequest request) {
        var dto = NoticeCommentCreateDto.builder()
                .memberId(memberService.findByEmail(memberEmail).getId())
                .noticeId(noticeId)
                .noticeCommentContent(request.getNoticeCommentContent())
                .build();
        var comment = noticeCommentService.create(dto);

        return new NoticeCommentResponse(comment.getId());
    }

    public NoticeCommentFindResponse findByNoticeCommentId(Long commentId) {
        var comment = noticeCommentService.findByNoticeCommentId(commentId);
        return NoticeCommentConverter.toNoticeCommentFindResponse(comment);
    }

    public NoticeCommentFindByNoticeIdAndPageResponse findByNoticeIdAndPage(Long noticeId, Pageable pageable) {
        var totalCount = noticeCommentService.count(noticeId);

        var dto = new NoticeCommentFindByNoticeIdAndPageDto(noticeId, pageable);
        var comments = noticeCommentService.findByNoticeIdAndPageDto(dto);

        return NoticeCommentConverter.toNoticeCommentFindByNoticeIdAndPageResponse(dto, totalCount, comments);
    }

    @Transactional
    public NoticeCommentResponse update(Long commentId, NoticeCommentRequest request) {
        var dto = new NoticeCommentUpdateDto(commentId, request.getNoticeCommentContent());
        var comment = noticeCommentService.update(dto);
        return new NoticeCommentResponse(comment.getId());
    }

    @Transactional
    public NoticeCommentResponse delete(Long commentId) {
        var comment = noticeCommentService.deleteByNoticeCommentId(commentId);
        return new NoticeCommentResponse(comment.getId());
    }
}
