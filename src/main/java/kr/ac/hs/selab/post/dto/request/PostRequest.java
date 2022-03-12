package kr.ac.hs.selab.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequest {
    @Schema(description = "게시글 제목")
    @NotNull(message = "게시글 제목 형식이 맞지 않습니다.")
    private String title;

    @Schema(description = "게시글 내용")
    @NotBlank(message = "게시글에 내용을 작성해야합니다.")
    private String content;
}
