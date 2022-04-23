package kr.ac.hs.selab.free_post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FreePostCreateDto {
    private final Long memberId;
    private final String title;
    private final String content;
}