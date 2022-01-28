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
    public PostResponse create(PostCreateDto dto, Member member, Board board) {
        Post post = postRepository.save(PostConverter.toPost(dto, member, board));
        return PostConverter.toPostResponse(post);
    }

    public PostResponse find(Long id) {
        return PostConverter.toPostResponse(findPost(id));
    }

    public Post findPost(Long id) {
        return postRepository.find(id, Constants.NOT_DELETED)
                .orElseThrow(() -> new NonExitsException(ErrorMessage.POST_NOT_EXISTS_ERROR));
    }

    public PostsResponse find(Board board) {
        List<Post> posts = postRepository.findByBoard(board);
        return PostConverter.toPostsResponse(posts);
    }

    @Transactional
    public PostResponse update(PostUpdateDto dto) {
        Post post = findPost(dto.getId()).update(dto.getTitle(), dto.getContent());
        return PostConverter.toPostResponse(post);
    }

    @Transactional
    public PostResponse delete(Long id) {
        Post post = findPost(id).delete();
        return PostConverter.toPostResponse(post);
    }

    @Transactional
    public void delete(Board board) {
        postRepository.deleteByBoard(board, Constants.DELETED);
    }
}
