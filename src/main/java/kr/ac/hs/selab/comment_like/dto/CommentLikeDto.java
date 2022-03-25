package kr.ac.hs.selab.comment_like.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentLikeDto {
    private final String memberEmail;
    private final Long commentId;
}
