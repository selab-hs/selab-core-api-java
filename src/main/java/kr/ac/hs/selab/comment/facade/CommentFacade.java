package kr.ac.hs.selab.comment.facade;

import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.domain.event.CommentEvent;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentFindByPostAndPageDto;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostAndPageResponse;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CommentFacade {
    private final ApplicationEventPublisher publisher;
    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public CommentResponse create(CommentCreateDto commentDto) {
        var member = memberService.findByEmail(commentDto.getMemberEmail());
        var post = postService.findPostById(commentDto.getPostId());
        var comment = commentService.create(member, post, commentDto.getContent());
        return new CommentResponse(comment.getId());
    }

    public CommentFindByPostAndPageResponse findCommentsResponseByPostId(CommentFindByPostAndPageDto dto) {
        var post = postService.findPostById(dto.getPostId());
        var totalCount = commentService.count(post);
        Page<Comment> comments = commentService.findCommentsByPost(post, dto.getPageable());

        return CommentConverter.toCommentsResponse(dto, totalCount, comments);
    }

    @Transactional
    public CommentResponse delete(Long id) {
        var comment = commentService.deleteByComment(id);
        publisher.publishEvent(CommentEvent.of(comment));

        return new CommentResponse(comment.delete().getId());
    }
}
