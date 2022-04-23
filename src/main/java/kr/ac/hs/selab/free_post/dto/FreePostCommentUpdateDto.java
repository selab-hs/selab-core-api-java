package kr.ac.hs.selab.free_post.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FreePostCommentUpdateDto {
    private final Long freePostCommentId;
    private final String freePostCommentContent;
}