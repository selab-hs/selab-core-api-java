package kr.ac.hs.selab.board.application;

import kr.ac.hs.selab.board.converter.PostConverter;
import kr.ac.hs.selab.board.domain.Post;
import kr.ac.hs.selab.board.dto.PostCreateDto;
import kr.ac.hs.selab.board.dto.PostUpdateDto;
import kr.ac.hs.selab.board.infrastructure.PostRepository;
import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post create(PostCreateDto dto, Long memberId, Long boardId) {
        return postRepository.save(PostConverter.toPost(dto, memberId, boardId));
    }

    public Long count(Long boardId) {
        return postRepository.countByBoardIdAndDeleteFlag(boardId, Constants.NOT_DELETED);
    }

    public Post findPostById(Long id) {
        return postRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.POST_NOT_EXISTS_ERROR));
    }

    public List<Post> findPostsByBoardId(Long boardId) {
        return postRepository.findByBoardIdAndDeleteFlag(boardId, Constants.NOT_DELETED);
    }

    public Page<Post> findPostsByBoardIdAndPage(Long boardId, Pageable pageable) {
        return postRepository.findByBoardIdAndDeleteFlag(boardId, Constants.NOT_DELETED, pageable);
    }

    @Transactional
    public Post update(PostUpdateDto dto) {
        return findPostById(dto.getId()).update(dto.getTitle(), dto.getContent());
    }

    @Transactional
    public Post deleteById(Long id) {
        return findPostById(id).delete();
    }

    @Transactional
    public void deleteByBoardId(Long boardId) {
        postRepository.findByBoardIdAndDeleteFlag(boardId, Constants.NOT_DELETED)
                .forEach(Post::delete);
    }
}
