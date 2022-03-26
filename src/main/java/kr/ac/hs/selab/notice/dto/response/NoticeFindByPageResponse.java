package kr.ac.hs.selab.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class NoticeFindByPageResponse {
    @Schema(description = "공지사항 전체 개수")
    private final Long totalCount;

    @Schema(description = "공지사항 아이디")
    private final Long noticeId;

    @Schema(description = "공지사항 페이지 번호")
    private final int pageNumber;

    @Schema(description = "한 페이지에 가져올 공지사항 수")
    private final int pageSize;

    @Schema(description = "공지사항 조회 정렬 기준")
    private final String sort;

    @Schema(description = "공지사항 전체 리스트")
    private final List<InnerClass> notices;

    @Builder
    @Getter
    public static class InnerClass {
        @Schema(description = "작성자 아이디")
        private final Long memberId;

        @Schema(description = "공지사항 제목")
        private final String title;

        @Schema(description = "공지사항 내용")
        private final String content;

        @Schema(description = "공지사항 생성 시간")
        private final LocalDateTime createdAt;

        @Schema(description = "공지사항 수정 시간")
        private final LocalDateTime modifiedAt;
    }
}
