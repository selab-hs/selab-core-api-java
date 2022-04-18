package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.domain.Comment;
import kr.ac.hs.selab.board.domain.CommentLike;
import kr.ac.hs.selab.board.infrastructure.CommentLikeRepository;
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
    public void deleteById(Long id) {
        commentLikeRepository.deleteById(id);
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
