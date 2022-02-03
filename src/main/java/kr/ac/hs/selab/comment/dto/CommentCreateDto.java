package kr.ac.hs.selab.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentCreateDto {
    private final String memberEmail;
    private final Long postId;
    private final String content;
}
