package kr.ac.hs.selab.free_post_comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FreePostCommentCreateDto {
    private final Long memberId;
    private final Long freePostId;
    private final String freePostCommentContent;
}
