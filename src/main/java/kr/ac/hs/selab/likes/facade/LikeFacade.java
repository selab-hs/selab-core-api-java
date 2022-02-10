package kr.ac.hs.selab.likes.facade;

import kr.ac.hs.selab.likes.application.LikeService;
import kr.ac.hs.selab.likes.dto.LikeCreateDto;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class LikeFacade {
    private final MemberService memberService;
    private final LikeService likeService;

    @Transactional
    public void createByLikeCreateDto(LikeCreateDto dto) {
        Member member = memberService.findByEmail(dto.getMemberEmail());
        likeService.createByLikeCreateDtoAndMember(dto, member);
    }
}
