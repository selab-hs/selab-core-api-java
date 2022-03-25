package kr.ac.hs.selab.notice_like.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NoticeLikeResponse {
    @Schema(description = "공지사항 좋아요 id")
    private final Long id;
}
