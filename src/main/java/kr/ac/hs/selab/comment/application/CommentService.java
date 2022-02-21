package kr.ac.hs.selab.comment.application;

import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.response.CommentFindResponse;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.comment.infrastructure.CommentRepository;
import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Comment create(Member member, Post post, String commentContent) {
        Comment comment = Comment.builder()
                .member(member)
                .post(post)
                .content(commentContent)
                .build();

        return commentRepository.save(comment);
    }

    public CommentFindResponse findCommentResponseById(Long id) {
        return CommentConverter.toCommentResponse(findCommentById(id));
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.COMMENT_NOT_EXISTS_ERROR));
    }

    public List<Comment> findCommentsByPost(Post post) {
        return commentRepository.findByPostAndDeleteFlag(post, Constants.NOT_DELETED);
    }

    @Transactional
    public CommentResponse update(CommentUpdateDto dto) {
        Comment comment = findCommentById(dto.getId()).update(dto.getContent());
        return new CommentResponse(comment.getId());
    }

    @Transactional
    public Comment deleteByComment(Long id) {
        return findCommentById(id).delete();
    }

    @Transactional
    public void deleteByPost(Post post) {
        commentRepository.findByPostAndDeleteFlag(post, Constants.NOT_DELETED)
                .forEach(Comment::delete);
    }

    @Transactional
    public void deleteByPosts(List<Post> posts) {
        posts.forEach(this::deleteByPost);
    }
}
