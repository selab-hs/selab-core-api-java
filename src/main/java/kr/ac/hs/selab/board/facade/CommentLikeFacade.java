package kr.ac.hs.selab.board.facade;

import kr.ac.hs.selab.board.application.CommentService;
import kr.ac.hs.selab.board.application.CommentLikeService;
import kr.ac.hs.selab.board.converter.CommentLikeConverter;
import kr.ac.hs.selab.board.dto.CommentLikeDto;
import kr.ac.hs.selab.board.dto.CommentLikeFindDto;
import kr.ac.hs.selab.board.dto.response.CommentLikeFindResponse;
import kr.ac.hs.selab.board.dto.response.CommentLikeResponse;
import kr.ac.hs.selab.member.application.MemberService;
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
        var member = memberService.findByEmail(dto.getMemberEmail());
        var comment = commentService.findCommentById(dto.getCommentId());

        var like = commentLikeService.create(member.getId(), comment.getId());
        return new CommentLikeResponse(like.getId());
    }

    public CommentLikeFindResponse find(CommentLikeFindDto dto) {
        var comment = commentService.findCommentById(dto.getCommentId());
        var likes = commentLikeService.find(comment.getId());

        return CommentLikeConverter.toCommentLikeFindResponse(comment.getId(), likes);
    }

    @Transactional
    public CommentLikeResponse delete(Long id) {
        commentLikeService.deleteById(id);
        return new CommentLikeResponse(id);
    }
}
