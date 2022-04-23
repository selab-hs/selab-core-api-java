package kr.ac.hs.selab.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NoticeResponse {
    @Schema(description = "공지사항 ID")
    private final Long id;
}