package kr.ac.hs.selab.notice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NoticeRequest {
    @Schema(description = "공지사항 제목")
    @Size(max = 30, message = "공지사항 제목이 너무 깁니다.")
    private String title;

    @Schema(description = "공지사항 내용")
    @NotBlank(message = "공지사항 내용을 입력해 주세요.")
    private String content;
}
