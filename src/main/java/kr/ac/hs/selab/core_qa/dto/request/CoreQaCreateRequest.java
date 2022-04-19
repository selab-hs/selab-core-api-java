package kr.ac.hs.selab.core_qa.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoreQaCreateRequest {
    @Schema(description = "질의응답 제목")
    @Size(max = 30, message = "QA 제목이 너무 깁니다.")
    private String title;

    @Schema(description = "질의응답 내용")
    @NotBlank(message = "QA의 내용을 입력 해주세요.")
    private String content;
}