package kr.ac.hs.selab.core_qa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CoreQaFindByPageResponse {
    @Schema(description = "질의응답 전체 개수")
    private final Long totalCount;

    @Schema(description = "질의응답 페이지 번호")
    private final int pageNumber;

    @Schema(description = "한 페이지에 가져올 질의응답 수")
    private final int pageSize;

    @Schema(description = "질의응답 조회 정렬 기준")
    private final String sort;

    @Schema(description = "질의응답 전체 리스트")
    private final List<CoreQaFindByIdResponse> coreQas;
}