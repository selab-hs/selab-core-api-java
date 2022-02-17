package kr.ac.hs.selab.commentLike.facade;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.commentLike.application.CommentLikeService;
import kr.ac.hs.selab.commentLike.dto.CommentLikeDto;
import kr.ac.hs.selab.commentLike.dto.CommentLikeFIndDto;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeFindResponse;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeResponse;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class CommentLikeFacade {
    private final MemberService memberService;
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    @Transactional
    public CommentLikeResponse create(CommentLikeDto dto) {
        Member member = memberService.findByEmail(dto.getMemberEmail());
        Comment comment = commentService.findCommentById(dto.getCommentId());

        return commentLikeService.create(member, comment);
    }

    public CommentLikeFindResponse find(CommentLikeFIndDto dto) {
        Comment comment = commentService.findCommentById(dto.getCommentId());
        return commentLikeService.find(comment);
    }
}
