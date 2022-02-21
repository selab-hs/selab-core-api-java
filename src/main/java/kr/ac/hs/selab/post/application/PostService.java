package kr.ac.hs.selab.post.application;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.common.utils.Constants;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostUpdateDto;
import kr.ac.hs.selab.post.dto.response.PostFindResponse;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import kr.ac.hs.selab.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post create(PostCreateDto dto, Member member, Board board) {
        return postRepository.save(PostConverter.toPost(dto, member, board));
    }

    public Long count(Board board) {
        return postRepository.countByBoardAndDeleteFlag(board, Constants.NOT_DELETED);
    }

    public PostFindResponse findPostResponseById(Long id) {
        return PostConverter.toPostFindResponse(findPostById(id));
    }

    public Post findPostById(Long id) {
        return postRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.POST_NOT_EXISTS_ERROR));
    }

    public List<Post> findPostsByBoard(Board board) {
        return postRepository.findByBoardAndDeleteFlag(board, Constants.NOT_DELETED);
    }

    @Transactional
    public PostResponse update(PostUpdateDto dto) {
        Post post = findPostById(dto.getId()).update(dto.getTitle(), dto.getContent());
        return new PostResponse(post.getId());
    }

    @Transactional
    public Post delete(Long id) {
        return findPostById(id).delete();
    }

    @Transactional
    public void deleteByBoard(Board board) {
        postRepository.findByBoardAndDeleteFlag(board, Constants.NOT_DELETED)
                .forEach(Post::delete);
    }
}
