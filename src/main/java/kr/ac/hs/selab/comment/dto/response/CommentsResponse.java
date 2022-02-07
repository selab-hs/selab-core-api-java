package kr.ac.hs.selab.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class CommentsResponse {
    @Schema(description = "댓글 전체 리스트")
    private final List<CommentResponse> comments;
}
