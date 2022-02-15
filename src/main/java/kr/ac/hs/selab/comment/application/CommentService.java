package kr.ac.hs.selab.comment.application;

import kr.ac.hs.selab.comment.converter.CommentConverter;
import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostResponse;
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
    public CommentResponse create(CommentCreateDto dto, Member member, Post post) {
        Comment comment = commentRepository.save(CommentConverter.toComment(dto, member, post));
        return new CommentResponse(comment.getId());
    }

    public CommentFindResponse findCommentResponseById(Long id) {
        return CommentConverter.toCommentResponse(findCommentById(id));
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.COMMENT_NOT_EXISTS_ERROR));
    }

    public CommentFindByPostResponse findCommentsResponseByPost(Post post) {
        Long totalCount = commentRepository.countByPostAndDeleteFlag(post, Constants.NOT_DELETED);
        List<Comment> comments = commentRepository.findByPostAndDeleteFlag(post, Constants.NOT_DELETED);

        return CommentConverter.toCommentsResponse(post.getId(), totalCount, comments);
    }

    @Transactional
    public CommentResponse update(CommentUpdateDto dto) {
        Comment comment = findCommentById(dto.getId()).update(dto.getContent());
        return new CommentResponse(comment.getId());
    }

    @Transactional
    public CommentResponse deleteById(Long id) {
        Comment comment = findCommentById(id).delete();
        return new CommentResponse(comment.getId());
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

    public void isDuplication(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new NonExitsException(ErrorMessage.COMMENT_NOT_EXISTS_ERROR);
        }
    }
}
