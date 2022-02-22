package kr.ac.hs.selab.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostFindByBoardAndPageDto {
    private final Long boardId;
    private final int pageNumber;
    private final int pageSize;
    private final String sortProperty;
}
