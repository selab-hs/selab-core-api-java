package kr.ac.hs.selab.commentLike.application;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.commentLike.domain.CommentLike;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeResponse;
import kr.ac.hs.selab.commentLike.infrastructure.CommentLikeRepository;
import kr.ac.hs.selab.member.domain.Member;
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
    public CommentLike create(Member member, Comment comment) {
        return commentLikeRepository.save(new CommentLike(member, comment));
    }

    public Long count(Comment comment) {
        return commentLikeRepository.countByComment(comment);
    }

    public List<CommentLike> find(Comment comment) {
        return commentLikeRepository.findByComment(comment);
    }

    @Transactional
    public CommentLikeResponse deleteById(Long id) {
        commentLikeRepository.deleteById(id);
        return new CommentLikeResponse(id);
    }

    @Transactional
    public void deleteByComment(Comment comment) {
        commentLikeRepository.deleteAll(commentLikeRepository.findByComment(comment));
    }

    @Transactional
    public void deleteByComments(List<Comment> comments) {
        comments.forEach(this::deleteByComment);
    }
}
