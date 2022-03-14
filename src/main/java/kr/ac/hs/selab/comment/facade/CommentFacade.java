package kr.ac.hs.selab.comment.facade;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentFindByPostIdAndPageDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostIdAndPageResponse;
import kr.ac.hs.selab.comment.dto.response.CommentFindResponse;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.commentLike.application.CommentLikeService;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentFacade {
    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    @Transactional
    public CommentResponse create(CommentCreateDto commentDto) {
        var member = memberService.findByEmail(commentDto.getMemberEmail());
        var post = postService.findPostById(commentDto.getPostId());
        var comment = commentService.create(member.getId(), post.getId(), commentDto.getContent());

        return new CommentResponse(comment.getId());
    }

    public CommentFindResponse findCommentResponseById(Long id) {
        var comment = commentService.findCommentById(id);
        return CommentConverter.toCommentFindResponse(comment);
    }

    public CommentFindByPostIdAndPageResponse findCommentsResponseByPostId(CommentFindByPostIdAndPageDto dto) {
        var post = postService.findPostById(dto.getPostId());
        var totalCount = commentService.count(post.getId());
        var comments = commentService.findCommentsByPostId(post.getId(), dto.getPageable());

        return CommentConverter.toCommentFindByPostIdAndPageResponse(dto, totalCount, comments);
    }

    @Transactional
    public CommentResponse update(CommentUpdateDto dto) {
        var comment = commentService.update(dto);
        return new CommentResponse(comment.getId());
    }

    @Transactional
    public CommentResponse delete(Long id) {
        var comment = commentService.deleteByComment(id);
        commentLikeService.deleteByCommentId(comment.getId());

        return new CommentResponse(comment.delete().getId());
    }
}
