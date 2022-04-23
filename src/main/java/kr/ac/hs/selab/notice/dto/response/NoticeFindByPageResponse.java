package kr.ac.hs.selab.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class NoticeFindByPageResponse {
    @Schema(description = "공지사항 전체 개수")
    private final Long totalCount;

    @Schema(description = "공지사항 페이지 번호")
    private final int pageNumber;

    @Schema(description = "한 페이지에 가져올 공지사항 수")
    private final int pageSize;

    @Schema(description = "공지사항 조회 정렬 기준")
    private final String sort;

    @Schema(description = "공지사항 전체 리스트")
    private final List<NoticeFindByIdResponse> notices;
}