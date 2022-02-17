package kr.ac.hs.selab.commentLike.converter;

import kr.ac.hs.selab.commentLike.domain.CommentLike;
import kr.ac.hs.selab.commentLike.dto.response.CommentLikeFindResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CommentLikeConverter {
    private CommentLikeFindResponse.CommentLikeInnerResponse toCommentLikeInnerResponse(CommentLike like) {
        return new CommentLikeFindResponse.CommentLikeInnerResponse(like.getId(), like.getMember().getEmail());
    }

    public CommentLikeFindResponse toCommentLikeFindResponse(Long commentId, Long totalCount, List<CommentLike> likes) {
        List<CommentLikeFindResponse.CommentLikeInnerResponse> commentLikeInnerResponses = likes.stream()
                .map(CommentLikeConverter::toCommentLikeInnerResponse)
                .collect(Collectors.toList());

        return CommentLikeFindResponse.builder()
                .commentId(commentId)
                .totalCount(totalCount)
                .commentLikes(commentLikeInnerResponses)
                .build();
    }
}
