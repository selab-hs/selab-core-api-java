package kr.ac.hs.selab.notice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NoticeCreateDto {
    private final Long memberId;
    private final String title;
    private final String content;
    private final String image;
}
