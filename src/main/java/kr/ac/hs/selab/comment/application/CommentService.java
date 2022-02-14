package kr.ac.hs.selab.comment.application;

import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.comment.dto.response.CommentsResponse;
import kr.ac.hs.selab.comment.infrastructure.CommentRepository;
import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse createByCommentCreateDto(CommentCreateDto dto, Member member, Post post) {
        Comment comment = commentRepository.save(CommentConverter.toComment(dto, member, post));
        return CommentConverter.toCommentResponse(comment);
    }

    public CommentResponse findCommentResponseById(Long id) {
        return CommentConverter.toCommentResponse(findCommentById(id));
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.COMMENT_NOT_EXISTS_ERROR));
    }

    public CommentsResponse findCommentsResponseByPost(Post post) {
        List<Comment> comments = commentRepository.findByPost(post);
        return CommentConverter.toCommentsResponse(comments);
    }

    @Transactional
    public CommentResponse updateByCommentUpdateDto(CommentUpdateDto dto) {
        Comment comment = findCommentById(dto.getId()).update(dto.getContent());
        return CommentConverter.toCommentResponse(comment);
    }

    @Transactional
    public CommentResponse deleteById(Long id) {
        Comment comment = findCommentById(id).delete();
        return CommentConverter.toCommentResponse(comment);
    }

    @Transactional
    public void deleteByPost(Post post) {
        commentRepository.deleteByPost(post, Constants.DELETED);
    }

    @Transactional
    public void deleteByPosts(List<Post> posts) {
        posts.forEach(this::deleteByPost);
    }
}
