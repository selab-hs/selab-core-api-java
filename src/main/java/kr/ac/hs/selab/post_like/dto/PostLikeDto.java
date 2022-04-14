package kr.ac.hs.selab.post_like.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostLikeDto {
    private final String memberEmail;
    private final Long postId;
}
