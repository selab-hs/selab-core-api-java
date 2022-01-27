package kr.ac.hs.selab.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PostRequest {
    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 내용")
    private String content;
}
