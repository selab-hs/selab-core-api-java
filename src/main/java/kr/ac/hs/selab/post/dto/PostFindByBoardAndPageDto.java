package kr.ac.hs.selab.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostFindByBoardAndPageDto {
    private final Long boardId;
    private final int page;
    private final int size;
}
