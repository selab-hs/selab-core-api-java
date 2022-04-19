package kr.ac.hs.selab.core_qa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CoreQaFindByIdResponse {
    @Schema(description = "작성자 아이디")
    private final Long id;

    @Schema(description = "질의응답 제목")
    private final String title;

    @Schema(description = "질의응답 내용")
    private final String content;

    @Schema(description = "질의응답 아이디")
    private final Long memberId;
}