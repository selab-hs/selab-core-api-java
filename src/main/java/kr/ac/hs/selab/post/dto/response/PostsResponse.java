package kr.ac.hs.selab.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class PostsResponse {
    @Schema(description = "게시글 전체 리스트")
    private final List<PostResponse> posts;
}
