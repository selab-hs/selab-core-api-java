package kr.ac.hs.selab.notice.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.notice.application.NoticeLikeService;
import kr.ac.hs.selab.notice.converter.NoticeLikeConverter;
import kr.ac.hs.selab.notice.dto.NoticeLikeDto;
import kr.ac.hs.selab.notice.dto.response.NoticeLikeFindResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class NoticeLikeFacade {
    private final MemberService memberService;
    private final NoticeLikeService noticeLikeService;

    @Transactional
    public NoticeLikeResponse create(String memberEmail, Long noticeId) {
        var memberId = memberService.findByEmail(memberEmail).getId();
        var dto = new NoticeLikeDto(memberId, noticeId);

        var like = noticeLikeService.create(dto);
        return new NoticeLikeResponse(like.getId());
    }

    public NoticeLikeFindResponse find(Long noticeId) {
        var likes = noticeLikeService.find(noticeId);
        return NoticeLikeConverter.toNoticeLikeFindResponse(noticeId, likes);
    }

    @Transactional
    public NoticeLikeResponse delete(Long noticeId) {
        noticeLikeService.deleteByNoticeLikeId(noticeId);
        return new NoticeLikeResponse(noticeId);
    }
}