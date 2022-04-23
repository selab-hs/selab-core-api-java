package kr.ac.hs.selab.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostLikeDto {
    private final String memberEmail;
    private final Long postId;
}