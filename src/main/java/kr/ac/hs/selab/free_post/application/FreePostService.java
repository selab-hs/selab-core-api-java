package kr.ac.hs.selab.free_post.application;

import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.free_post.converter.FreePostConverter;
import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post.dto.FreePostCreateDto;
import kr.ac.hs.selab.free_post.dto.FreePostUpdateDto;
import kr.ac.hs.selab.free_post.infrastructure.FreePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FreePostService {
    private final FreePostRepository freePostRepository;

    @Transactional
    public FreePost create(FreePostCreateDto dto) {
        return freePostRepository.save(FreePostConverter.toFreePost(dto));
    }

    public Long count() {
        return freePostRepository.countByDeleteFlag(Constants.NOT_DELETED);
    }

    public FreePost findById(Long id) {
        return freePostRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.FREE_POST_NOT_EXISTS_ERROR));
    }

    public Page<FreePost> findByPage(Pageable pageable) {
        return freePostRepository.findByDeleteFlag(Constants.NOT_DELETED, pageable);
    }

    @Transactional
    public FreePost update(FreePostUpdateDto dto) {
        return findById(dto.getId()).update(dto.getTitle(), dto.getContent());
    }

    @Transactional
    public FreePost delete(Long id) {
        return findById(id).delete();
    }
}
