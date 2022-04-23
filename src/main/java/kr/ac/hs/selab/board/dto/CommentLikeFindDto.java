package kr.ac.hs.selab.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentLikeFindDto {
    private final Long commentId;
}