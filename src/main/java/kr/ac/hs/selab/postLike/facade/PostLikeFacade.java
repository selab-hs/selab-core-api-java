package kr.ac.hs.selab.postLike.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.postLike.application.PostLikeService;
import kr.ac.hs.selab.postLike.dto.PostLikeDto;
import kr.ac.hs.selab.postLike.dto.PostLikeFIndDto;
import kr.ac.hs.selab.postLike.dto.response.PostLikeFindResponse;
import kr.ac.hs.selab.postLike.dto.response.PostLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class PostLikeFacade {
    private final MemberService memberService;
    private final PostService postService;
    private final PostLikeService postLikeService;

    @Transactional
    public PostLikeResponse create(PostLikeDto dto) {
        Member member = memberService.findByEmail(dto.getMemberEmail());
        Post post = postService.findPostById(dto.getPostId());

        return postLikeService.create(member, post);
    }

    public PostLikeFindResponse find(PostLikeFIndDto dto) {
        Post post = postService.findPostById(dto.getPostId());
        return postLikeService.find(post);
    }
}
