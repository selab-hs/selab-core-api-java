package kr.ac.hs.selab.board.converter;

import kr.ac.hs.selab.board.domain.CommentLike;
import kr.ac.hs.selab.board.dto.response.CommentLikeFindResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CommentLikeConverter {
    public CommentLikeFindResponse toCommentLikeFindResponse(Long commentId, List<CommentLike> likes) {
        return CommentLikeFindResponse.builder()
                .commentId(commentId)
                .totalCount((long) likes.size())
                .commentLikes(
                        likes.stream()
                                .map(CommentLikeConverter::toCommentLikeInnerResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private CommentLikeFindResponse.CommentLikeInnerResponse toCommentLikeInnerResponse(CommentLike like) {
        return new CommentLikeFindResponse.CommentLikeInnerResponse(like.getId(), like.getMemberId());
    }
}
