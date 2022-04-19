package kr.ac.hs.selab.board.converter;

import kr.ac.hs.selab.board.domain.PostLike;
import kr.ac.hs.selab.board.dto.response.PostLikeFindResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PostLikeConverter {
    public PostLikeFindResponse toPostLikeFindResponse(Long postId, List<PostLike> likes) {
        return PostLikeFindResponse.builder()
                .postId(postId)
                .totalCount((long) likes.size())
                .postLikes(
                        likes.stream()
                                .map(PostLikeConverter::toPostLikeInnerResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private PostLikeFindResponse.PostLikeInnerResponse toPostLikeInnerResponse(PostLike like) {
        return new PostLikeFindResponse.PostLikeInnerResponse(like.getId(), like.getMemberId());
    }
}
