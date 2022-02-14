package kr.ac.hs.selab.likes.facade;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.likes.application.LikeService;
import kr.ac.hs.selab.likes.domain.vo.TargetType;
import kr.ac.hs.selab.likes.dto.LikeCountDto;
import kr.ac.hs.selab.likes.dto.LikeCreateDto;
import kr.ac.hs.selab.likes.dto.response.LikeCountResponse;
import kr.ac.hs.selab.likes.dto.response.LikeResponse;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class LikeFacade {
    private final MemberService memberService;
    private final LikeService likeService;
    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public LikeResponse createByLikeCreateDto(LikeCreateDto dto) {
        Member member = memberService.findByEmail(dto.getMemberEmail());
        checkDuplicateTarget(dto.getTargetType(), dto.getTargetId());

        return likeService.createByLikeCreateDtoAndMember(dto, member);
    }

    public LikeCountResponse findLikeCountResponseByLikeCountDto(LikeCountDto dto) {
        checkDuplicateTarget(dto.getTargetType(), dto.getTargetId());
        return likeService.findLikeCountResponseByLikeCountDto(dto);
    }

    private void checkDuplicateTarget(TargetType targetType, Long targetId) {
        if (TargetType.POST.equals(targetType)) {
            postService.isDuplication(targetId);
        }
        if (TargetType.COMMENT.equals(targetType)) {
            commentService.isDuplication(targetId);
        }
    }
}
