package kr.ac.hs.selab.commentLike.application;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.commentLike.domain.CommentLike;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeResponse;
import kr.ac.hs.selab.commentLike.infrastructure.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public CommentLike create(Long memberId, Long commentId) {
        return commentLikeRepository.save(new CommentLike(memberId, commentId));
    }

    public List<CommentLike> find(Long commentId) {
        return commentLikeRepository.findByCommentId(commentId);
    }

    @Transactional
    public CommentLikeResponse deleteById(Long id) {
        commentLikeRepository.deleteById(id);
        return new CommentLikeResponse(id);
    }

    @Transactional
    public void deleteByCommentId(Long commentId) {
        commentLikeRepository.deleteAll(commentLikeRepository.findByCommentId(commentId));
    }

    @Transactional
    public void deleteByComments(List<Comment> comments) {
        comments.forEach(comment -> deleteByCommentId(comment.getId()));
    }
}
