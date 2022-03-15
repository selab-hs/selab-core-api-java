package kr.ac.hs.selab.comment.application;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.infrastructure.CommentRepository;
import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Comment create(Long memberId, Long postId, String commentContent) {
        var comment = Comment.builder()
                .memberId(memberId)
                .postId(postId)
                .content(commentContent)
                .build();

        return commentRepository.save(comment);
    }

    public Long count(Long postId) {
        return commentRepository.countByPostIdAndDeleteFlag(postId, Constants.NOT_DELETED);
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.COMMENT_NOT_EXISTS_ERROR));
    }

    public List<Comment> findCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdAndDeleteFlag(postId, Constants.NOT_DELETED);
    }

    public Page<Comment> findCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostIdAndDeleteFlag(postId, Constants.NOT_DELETED, pageable);
    }

    @Transactional
    public Comment update(CommentUpdateDto dto) {
        return findCommentById(dto.getId()).update(dto.getContent());
    }

    @Transactional
    public Comment deleteByComment(Long id) {
        return findCommentById(id).delete();
    }

    @Transactional
    public void deleteByPost(Long postId) {
        findCommentsByPostId(postId).forEach(Comment::delete);
    }

    @Transactional
    public void deleteByPosts(List<Post> posts) {
        posts.forEach(post -> deleteByPost(post.getId()));
    }
}
