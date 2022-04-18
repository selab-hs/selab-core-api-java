package kr.ac.hs.selab.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostCreateDto {
    private final String memberEmail;
    private final Long boardId;
    private final String title;
    private final String content;
}
