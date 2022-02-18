package kr.ac.hs.selab.commentLike.application;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.commentLike.converter.CommentLikeConverter;
import kr.ac.hs.selab.commentLike.domain.CommentLike;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeFindResponse;
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
    public CommentLikeResponse create(Member member, Comment comment) {
        CommentLike like = commentLikeRepository.save(new CommentLike(member, comment));
        return new CommentLikeResponse(like.getId());
    }

    public CommentLikeFindResponse find(Comment comment) {
        Long totalCount = commentLikeRepository.countByComment(comment);
        List<CommentLike> likes = commentLikeRepository.findByComment(comment);

        return CommentLikeConverter.toCommentLikeFindResponse(comment.getId(), totalCount, likes);
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
}
