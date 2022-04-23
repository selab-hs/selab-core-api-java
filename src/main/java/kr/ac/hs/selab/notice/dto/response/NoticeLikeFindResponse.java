package kr.ac.hs.selab.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Getter
public class NoticeLikeFindResponse {
    @Schema(description = "공지사항 id")
    private final Long noticeId;

    @Schema(description = "공지사항 좋아요 전체 개수")
    private final Long totalCount;

    @Schema(description = "공지사항 좋아요 전체 목록")
    private final List<NoticeLikeInnerResponse> noticeLikes;

    @RequiredArgsConstructor
    @Getter
    public static class NoticeLikeInnerResponse {
        @Schema(description = "공지사항 좋아요 id")
        private final Long id;

        @Schema(description = "공지사항 좋아요한 회원 id")
        private final Long memberId;
    }
}