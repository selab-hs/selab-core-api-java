package kr.ac.hs.selab.notice_comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NoticeCommentCreateDto {
    private final Long memberId;
    private final Long noticeId;
    private final String noticeCommentContent;
}
