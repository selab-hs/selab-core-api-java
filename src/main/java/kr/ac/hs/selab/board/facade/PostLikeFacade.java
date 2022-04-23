package kr.ac.hs.selab.board.facade;

import kr.ac.hs.selab.board.application.PostLikeService;
import kr.ac.hs.selab.board.application.PostService;
import kr.ac.hs.selab.board.converter.PostLikeConverter;
import kr.ac.hs.selab.board.dto.PostLikeDto;
import kr.ac.hs.selab.board.dto.PostLikeFindDto;
import kr.ac.hs.selab.board.dto.response.PostLikeFindResponse;
import kr.ac.hs.selab.board.dto.response.PostLikeResponse;
import kr.ac.hs.selab.member.application.MemberService;
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
