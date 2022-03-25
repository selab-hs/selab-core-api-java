package kr.ac.hs.selab.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class NoticeFindResponse {
    @Schema(description = "작성자 아이디")
    private final Long memberId;

    @Schema(description = "공지사항 아이디")
    private final Long noticeId;

    @Schema(description = "공지사항 제목")
    private final String title;

    @Schema(description = "공지사항 내용")
    private final String content;

    @Schema(description = "공지사항 이미지")
    private final String image;

    @Schema(description = "공지사항 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "공지사항 수정 시간")
    private final LocalDateTime modifiedAt;
}
