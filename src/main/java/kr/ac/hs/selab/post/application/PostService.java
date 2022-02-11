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
import kr.ac.hs.selab.post.dto.response.PostResponse;
import kr.ac.hs.selab.post.dto.response.PostsResponse;
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
    public PostResponse createByPostCreateDto(PostCreateDto dto, Member member, Board board) {
        Post post = postRepository.save(PostConverter.toPost(dto, member, board));
        return PostConverter.toPostResponse(post);
    }

    public PostResponse findPostResponseById(Long id) {
        return PostConverter.toPostResponse(findPostById(id));
    }

    public Post findPostById(Long id) {
        return postRepository.findByIdAndDeleteFlag(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.POST_NOT_EXISTS_ERROR));
    }

    public PostsResponse findPostsResponseByBoard(Board board) {
        List<Post> posts = findPostByBoard(board);
        return PostConverter.toPostsResponse(posts);
    }

    public List<Post> findPostByBoard(Board board) {
        return postRepository.findByBoard(board);
    }

    @Transactional
    public PostResponse updateByPostUpdateDto(PostUpdateDto dto) {
        Post post = findPostById(dto.getId()).update(dto.getTitle(), dto.getContent());
        return PostConverter.toPostResponse(post);
    }

    @Transactional
    public Post deleteById(Long id) {
        return findPostById(id).delete();
    }

    @Transactional
    public void deleteByBoard(Board board) {
        postRepository.deleteByBoard(board, Constants.DELETED);
    }

    public void isDuplication(Long id) {
        if (!postRepository.existsById(id)) {
            throw new NonExitsException(ErrorMessage.POST_NOT_EXISTS_ERROR);
        }
    }
}
