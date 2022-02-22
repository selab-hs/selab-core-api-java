package kr.ac.hs.selab.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class PostFindByBoardAndPageRequest {
    @Schema(description = "게시글 페이지")
    private int page;

    @Schema(description = "게시글 제목")
    @Range(min = 1, max = 100, message = "페이지 사이즈는 1부터 100까지 가능합니다.")
    private int size;
}
