package kr.ac.hs.selab.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class PostRequest {
    @Schema(description = "게시글 제목")
    @Pattern(regexp = "^[가-힣]{2,20}$", message = "게시글 제목 형식이 맞지 않습니다.")
    private String title;

    @Schema(description = "게시글 내용")
    private String content;
}
