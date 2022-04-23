package kr.ac.hs.selab.notice.dto;

import kr.ac.hs.selab.notice.domain.Notice;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Builder
@Getter
public class NoticeFindByPageDto {
    private final Long totalCount;
    private final Pageable pageable;
    private final Page<Notice> notices;
}