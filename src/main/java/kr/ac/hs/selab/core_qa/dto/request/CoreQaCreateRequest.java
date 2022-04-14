package kr.ac.hs.selab.core_qa.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CoreQaCreateRequest {
    @Size(max = 30, message = "QA 제목이 너무 깁니다.")
    private String title;
    @NotBlank(message = "QA의 내용을 입력 해주세요.")
    private String content;
}