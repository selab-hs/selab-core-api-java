package kr.ac.hs.selab.postLike.application;

import kr.ac.hs.selab.postLike.domain.PostLike;
import kr.ac.hs.selab.postLike.dto.response.PostLikeResponse;
import kr.ac.hs.selab.postLike.infrastructure.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public PostLike create(Long memberId, Long postId) {
        return postLikeRepository.save(new PostLike(memberId, postId));
    }

    public List<PostLike> find(Long postId) {
        return postLikeRepository.findByPostId(postId);
    }

    @Transactional
    public PostLikeResponse delete(Long id) {
        postLikeRepository.deleteById(id);
        return new PostLikeResponse(id);
    }

    @Transactional
    public void deleteByPostId(Long postId) {
        postLikeRepository.deleteAll(postLikeRepository.findByPostId(postId));
    }
}
