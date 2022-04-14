package kr.ac.hs.selab.post_like.application;

import kr.ac.hs.selab.post_like.domain.PostLike;
import kr.ac.hs.selab.post_like.infrastructure.PostLikeRepository;
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
    public void delete(Long id) {
        postLikeRepository.deleteById(id);
    }

    @Transactional
    public void deleteByPostId(Long postId) {
        postLikeRepository.deleteAll(postLikeRepository.findByPostId(postId));
    }
}
