package kr.ac.hs.selab.postLike.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.postLike.application.PostLikeService;
import kr.ac.hs.selab.postLike.converter.PostLikeConverter;
import kr.ac.hs.selab.postLike.domain.PostLike;
import kr.ac.hs.selab.postLike.dto.PostLikeDto;
import kr.ac.hs.selab.postLike.dto.PostLikeFIndDto;
import kr.ac.hs.selab.postLike.dto.response.PostLikeFindResponse;
import kr.ac.hs.selab.postLike.dto.response.PostLikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        PostLike like = postLikeService.create(member, post);
        return new PostLikeResponse(like.getId());
    }

    public PostLikeFindResponse find(PostLikeFIndDto dto) {
        Post post = postService.findPostById(dto.getPostId());

        Long totalCount = postLikeService.count(post);
        List<PostLike> likes = postLikeService.find(post);

        return PostLikeConverter.toPostLikeFindResponse(post.getId(), totalCount, likes);
    }
}
