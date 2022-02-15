package kr.ac.hs.selab.comment.facade;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.comment.dto.response.CommentsResponse;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentFacade {
    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public CommentResponse create(CommentCreateDto commentDto) {
        Member member = memberService.findByEmail(commentDto.getMemberEmail());
        Post post = postService.findPostById(commentDto.getPostId());

        return commentService.create(commentDto, member, post);
    }

    public CommentsResponse findCommentsResponseByPostId(Long postId) {
        Post post = postService.findPostById(postId);
        return commentService.findCommentsResponseByPost(post);
    }
}
