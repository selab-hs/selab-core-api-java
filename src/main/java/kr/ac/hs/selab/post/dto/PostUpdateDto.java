package kr.ac.hs.selab.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostUpdateDto {
    private final Long id;
    private final String title;
    private final String content;
}
