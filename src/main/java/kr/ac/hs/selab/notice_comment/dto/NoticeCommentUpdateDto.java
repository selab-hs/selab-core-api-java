package kr.ac.hs.selab.notice_comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NoticeCommentUpdateDto {
    private final Long noticeCommentId;
    private final String noticeCommentContent;
}
