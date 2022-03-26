package kr.ac.hs.selab.notice_comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class NoticeCommentFindByNoticeIdAndPageResponse {
    @Schema(description = "공지사항 id")
    private final Long noticeId;

    @Schema(description = "공지사항 댓글 전체 개수")
    private final Long totalCount;

    @Schema(description = "댓글 페이지 넘버")
    private final int pageNumber;

    @Schema(description = "한 페이지에 가져올 댓글 수")
    private final int pageSize;

    @Schema(description = "댓글 정렬 기준")
    private final String sort;

    @Schema(description = "공지사항의 댓글 전체 목록")
    private final List<NoticeCommentInnerResponse> noticeComments;

    @Builder
    @Getter
    public static class NoticeCommentInnerResponse {
        @Schema(description = "작성자 id")
        private final Long memberId;

        @Schema(description = "공지사항의 댓글 id")
        private final Long noticeCommentId;

        @Schema(description = "공지사항의 댓글 내용")
        private final String noticeCommentContent;

        @Schema(description = "공지사항의 댓글 생성 시간")
        private final LocalDateTime createdAt;

        @Schema(description = "공지사항의 댓글 수정 시간")
        private final LocalDateTime modifiedAt;
    }
}
