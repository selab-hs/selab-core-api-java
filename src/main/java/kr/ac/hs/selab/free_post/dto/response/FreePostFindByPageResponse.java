package kr.ac.hs.selab.free_post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FreePostFindByPageResponse {
    @Schema(description = "자유게시글 전체 개수")
    private final Long totalCount;

    @Schema(description = "자유게시글 페이지 번호")
    private final int pageNumber;

    @Schema(description = "한 페이지에 가져올 자유게시글 수")
    private final int pageSize;

    @Schema(description = "자유게시글 조회 정렬 기준")
    private final String sort;

    @Schema(description = "자유게시글 전체 리스트")
    private final List<FreePostFindByIdResponse> freePosts;
}
