package kr.ac.hs.selab.postLike.application;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
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
    public PostLike create(Member member, Post post) {
        return postLikeRepository.save(new PostLike(member, post));
    }

    public List<PostLike> find(Post post) {
        return postLikeRepository.findByPost(post);
    }

    @Transactional
    public PostLikeResponse delete(Long id) {
        postLikeRepository.deleteById(id);
        return new PostLikeResponse(id);
    }

    @Transactional
    public void deleteByPost(Post post) {
        postLikeRepository.deleteAll(postLikeRepository.findByPost(post));
    }
}
