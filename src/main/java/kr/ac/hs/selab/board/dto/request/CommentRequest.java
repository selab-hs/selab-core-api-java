package kr.ac.hs.selab.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentRequest {
    @Schema(description = "댓글 내용")
    @NotBlank(message = "댓글에 내용을 작성해야합니다.")
    private String content;
}