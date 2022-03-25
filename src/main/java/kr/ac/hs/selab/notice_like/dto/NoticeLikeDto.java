package kr.ac.hs.selab.notice_like.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NoticeLikeDto {
    private final Long memberId;
    private final Long noticeId;
}
