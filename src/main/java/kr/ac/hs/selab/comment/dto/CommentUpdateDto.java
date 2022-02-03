package kr.ac.hs.selab.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentUpdateDto {
    private final Long id;
    private final String content;
}
