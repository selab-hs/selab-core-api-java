package kr.ac.hs.selab.notice.converter;

import kr.ac.hs.selab.notice.domain.NoticeLike;
import kr.ac.hs.selab.notice.dto.response.NoticeLikeFindResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class NoticeLikeConverter {
    public NoticeLikeFindResponse toNoticeLikeFindResponse(Long noticeId, List<NoticeLike> likes) {
        return NoticeLikeFindResponse.builder()
                .noticeId(noticeId)
                .totalCount((long) likes.size())
                .noticeLikes(
                        likes.stream()
                                .map(NoticeLikeConverter::toNoticeLikeInnerResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private NoticeLikeFindResponse.NoticeLikeInnerResponse toNoticeLikeInnerResponse(NoticeLike like) {
        return new NoticeLikeFindResponse.NoticeLikeInnerResponse(like.getId(), like.getMemberId());
    }
}