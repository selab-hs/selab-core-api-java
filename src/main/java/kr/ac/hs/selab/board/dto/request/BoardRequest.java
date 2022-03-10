package kr.ac.hs.selab.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardRequest {
    @Schema(description = "게시판 제목")
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "게시판 제목 형식이 맞지 않습니다.")
    private String title;

    @Schema(description = "게시판 설명")
    @Size(max = 30, message = "게시판 설명이 너무 깁니다.")
    private String description;
}
