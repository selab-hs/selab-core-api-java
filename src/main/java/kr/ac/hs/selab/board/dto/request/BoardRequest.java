package kr.ac.hs.selab.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class BoardRequest {
    @Schema(description = "게시판 제목")
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "게시판 제목 형식이 맞지 않습니다.")
    private String title;

    @Schema(description = "게시판 설명")
    private String description;
}
