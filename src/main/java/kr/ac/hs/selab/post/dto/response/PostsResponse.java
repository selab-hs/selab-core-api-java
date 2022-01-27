package kr.ac.hs.selab.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PostsResponse {
    @Schema(description = "게시글 전체 리스트")
    private final List<PostResponse> posts;

    public static PostsResponse of(List<PostResponse> posts) {
        return new PostsResponse(posts);
    }
}
