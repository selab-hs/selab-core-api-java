package kr.ac.hs.selab.free_post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FreePostCommentRequest {
    @Schema(description = "자유게시글의 댓글 내용")
    @NotBlank(message = "자유게시글의 댓글에 내용을 작성해야합니다.")
    private String freePostCommentContent;
}