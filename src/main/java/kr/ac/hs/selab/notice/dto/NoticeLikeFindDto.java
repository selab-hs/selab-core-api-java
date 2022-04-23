package kr.ac.hs.selab.notice.dto;

import kr.ac.hs.selab.notice.domain.NoticeLike;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class NoticeLikeFindDto {
    private final Long noticeId;
    private final List<NoticeLike> noticeLikes;
}