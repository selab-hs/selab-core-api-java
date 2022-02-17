package kr.ac.hs.selab.postLike.application;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.postLike.converter.PostLikeConverter;
import kr.ac.hs.selab.postLike.domain.PostLike;
import kr.ac.hs.selab.postLike.dto.response.PostLikeFindResponse;
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
    public PostLikeResponse create(Member member, Post post) {
        PostLike like = postLikeRepository.save(new PostLike(member, post));
        return new PostLikeResponse(like.getId());
    }

    public PostLikeFindResponse find(Post post) {
        Long totalCount = postLikeRepository.countByPost(post);
        List<PostLike> likes = postLikeRepository.findByPost(post);

        return PostLikeConverter.toPostLikeFindResponse(post.getId(), totalCount, likes);
    }

    @Transactional
    public PostLikeResponse delete(Long id) {
        postLikeRepository.deleteById(id);

        return new PostLikeResponse(id);
    }
}
