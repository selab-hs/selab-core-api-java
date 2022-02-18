package kr.ac.hs.selab.postLike.converter;

import kr.ac.hs.selab.postLike.domain.PostLike;
import kr.ac.hs.selab.postLike.dto.response.PostLikeFindResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PostLikeConverter {
    public PostLikeFindResponse toPostLikeFindResponse(Long postId, Long totalCount, List<PostLike> likes) {
        return PostLikeFindResponse.builder()
                .postId(postId)
                .totalCount(totalCount)
                .postLikes(
                        likes.stream()
                                .map(
                                        like -> new PostLikeFindResponse.PostLikeInnerResponse(like.getId(), like.getMember().getEmail())
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }
}
