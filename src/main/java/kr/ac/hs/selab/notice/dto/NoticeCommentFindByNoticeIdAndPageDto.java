package kr.ac.hs.selab.notice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Getter
public class NoticeCommentFindByNoticeIdAndPageDto {
    private final Long noticeId;
    private final Pageable pageable;
}
