package kr.ac.hs.selab.free_post.facade;

import kr.ac.hs.selab.free_post.application.FreePostCommentService;
import kr.ac.hs.selab.free_post.converter.FreePostCommentConverter;
import kr.ac.hs.selab.free_post.dto.FreePostCommentCreateDto;
import kr.ac.hs.selab.free_post.dto.FreePostCommentFindByFreePostIdAndPageDto;
import kr.ac.hs.selab.free_post.dto.FreePostCommentUpdateDto;
import kr.ac.hs.selab.free_post.dto.request.FreePostCommentRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostCommentFindByFreePostIdAndPageResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostCommentFindResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostCommentResponse;
import kr.ac.hs.selab.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class FreePostCommentFacade {
    private final MemberService memberService;
    private final FreePostCommentService noticeCommentService;

    @Transactional
    public FreePostCommentResponse create(Long freePostId, String memberEmail, FreePostCommentRequest request) {
        var dto = FreePostCommentCreateDto.builder()
                .memberId(memberService.findByEmail(memberEmail).getId())
                .freePostId(freePostId)
                .freePostCommentContent(request.getFreePostCommentContent())
                .build();
        var comment = noticeCommentService.create(dto);

        return new FreePostCommentResponse(comment.getId());
    }

    public FreePostCommentFindResponse findByFreePostCommentId(Long commentId) {
        var comment = noticeCommentService.findByFreePostCommentId(commentId);
        return FreePostCommentConverter.toFreePostCommentFindResponse(comment);
    }

    public FreePostCommentFindByFreePostIdAndPageResponse findByFreePostIdAndPage(Long freePostId, Pageable pageable) {
        var totalCount = noticeCommentService.count(freePostId);

        var dto = new FreePostCommentFindByFreePostIdAndPageDto(freePostId, pageable);
        var comments = noticeCommentService.findByFreePostIdAndPageDto(dto);

        return FreePostCommentConverter.toFreePostCommentFindByFreePostIdAndPageResponse(dto, totalCount, comments);
    }

    @Transactional
    public FreePostCommentResponse update(Long commentId, FreePostCommentRequest request) {
        var dto = new FreePostCommentUpdateDto(commentId, request.getFreePostCommentContent());
        var comment = noticeCommentService.update(dto);
        return new FreePostCommentResponse(comment.getId());
    }

    @Transactional
    public FreePostCommentResponse delete(Long commentId) {
        var comment = noticeCommentService.deleteByFreePostCommentId(commentId);
        return new FreePostCommentResponse(comment.getId());
    }
}