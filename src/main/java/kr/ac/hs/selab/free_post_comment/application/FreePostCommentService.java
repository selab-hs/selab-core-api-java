package kr.ac.hs.selab.free_post_comment.application;

import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post_comment.converter.FreePostCommentConverter;
import kr.ac.hs.selab.free_post_comment.domain.FreePostComment;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentCreateDto;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentFindByFreePostIdAndPageDto;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentUpdateDto;
import kr.ac.hs.selab.free_post_comment.infrastructure.FreePostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FreePostCommentService {
    private final FreePostCommentRepository freePostCommentRepository;

    @Transactional
    public FreePostComment create(FreePostCommentCreateDto dto) {
        var comment = FreePostCommentConverter.toFreePostComment(dto);
        return freePostCommentRepository.save(comment);
    }

    public Long count(Long freePostId) {
        return freePostCommentRepository.countByFreePostIdAndDeleteFlag(freePostId, Constants.NOT_DELETED);
    }

    public FreePostComment findByFreePostCommentId(Long commentId) {
        return freePostCommentRepository.findByIdAndDeleteFlag(commentId, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.FREE_POST_COMMENT_NOT_EXISTS_ERROR));
    }

    public List<FreePostComment> findByFreePostId(Long freePostId) {
        return freePostCommentRepository.findByFreePostIdAndDeleteFlag(freePostId, Constants.NOT_DELETED);
    }

    public Page<FreePostComment> findByFreePostIdAndPageDto(FreePostCommentFindByFreePostIdAndPageDto dto) {
        return freePostCommentRepository.findByFreePostIdAndDeleteFlag(
                dto.getFreePostId(),
                Constants.NOT_DELETED,
                dto.getPageable()
        );
    }

    @Transactional
    public FreePostComment update(FreePostCommentUpdateDto dto) {
        return findByFreePostCommentId(dto.getFreePostCommentId()).update(dto.getFreePostCommentContent());
    }

    @Transactional
    public FreePostComment deleteByFreePostCommentId(Long commentId) {
        return findByFreePostCommentId(commentId).delete();
    }

    @Transactional
    public void deleteByFreePostId(Long freePostId) {
        findByFreePostId(freePostId).forEach(FreePostComment::delete);
    }

    @Transactional
    public void deleteByFreePosts(List<FreePost> freePosts) {
        freePosts.forEach(freePost -> deleteByFreePostId(freePost.getId()));
    }
}
