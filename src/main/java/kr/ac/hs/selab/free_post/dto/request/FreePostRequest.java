package kr.ac.hs.selab.free_post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FreePostRequest {
    @Schema(description = "자유게시글 제목")
    @Size(max = 30, message = "자유게시글의 제목이 너무 깁니다.")
    private String title;

    @Schema(description = "자유게시글 내용")
    @NotBlank(message = "자유게시글의 내용을 입력해 주세요.")
    private String content;
}
