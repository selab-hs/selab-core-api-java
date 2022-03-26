package kr.ac.hs.selab.free_post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FreePostResponse {
    @Schema(description = "자유게시글 id")
    private final Long id;
}
