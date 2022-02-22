package kr.ac.hs.selab.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class PostFindByBoardAndPageResponse {
    @Schema(description = "게시판 id")
    private final Long boardId;

    @Schema(description = "게시글 전체 개수")
    private final Long totalCount;

    @Schema(description = "게시글 페이지")
    private final int pageNumber;

    @Schema(description = "한 페이지에 가져올 게시글 수")
    private final int pageSize;

    @Schema(description = "게시글 정렬 기준")
    private final String sortProperty;

    @Schema(description = "게시글 전체 리스트")
    private final List<PostInnerResponse> posts;

    @Builder
    @Getter
    public static class PostInnerResponse {
        @Schema(description = "작성자 이메일")
        private final String memberEmail;

        @Schema(description = "게시글 id")
        private final Long postId;

        @Schema(description = "게시글 제목")
        private final String title;

        @Schema(description = "게시글 내용")
        private final String content;

        @Schema(description = "게시글 생성 시간")
        private final LocalDateTime createdAt;

        @Schema(description = "게시글 수정 시간")
        private final LocalDateTime modifiedAt;
    }
}
