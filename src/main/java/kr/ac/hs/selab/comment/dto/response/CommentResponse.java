package kr.ac.hs.selab.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;
import kr.ac.hs.selab.post.dto.response.PostFindResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentResponse {
    @Schema(description = "회원 정보")
    private final MemberCreateResponse member;

    @Schema(description = "게시글 정보")
    private final PostFindResponse post;

    @Schema(description = "댓글 내용")
    private final String content;

    @Schema(description = "댓글 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "댓글 수정 시간")
    private final LocalDateTime modifiedAt;
}
