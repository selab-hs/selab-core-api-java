package kr.ac.hs.selab.post_like.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post_like.application.PostLikeService;
import kr.ac.hs.selab.post_like.converter.PostLikeConverter;
import kr.ac.hs.selab.post_like.dto.PostLikeDto;
import kr.ac.hs.selab.post_like.dto.PostLikeFindDto;
import kr.ac.hs.selab.post_like.dto.response.PostLikeFindResponse;
import kr.ac.hs.selab.post_like.dto.response.PostLikeResponse;
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
        var member = memberService.findByEmail(dto.getMemberEmail());
        var post = postService.findPostById(dto.getPostId());

        var like = postLikeService.create(member.getId(), post.getId());
        return new PostLikeResponse(like.getId());
    }

    public PostLikeFindResponse find(PostLikeFindDto dto) {
        var post = postService.findPostById(dto.getPostId());
        var likes = postLikeService.find(post.getId());

        return PostLikeConverter.toPostLikeFindResponse(post.getId(), likes);
    }

    @Transactional
    public PostLikeResponse delete(Long id) {
        postLikeService.delete(id);
        return new PostLikeResponse(id);
    }
}
