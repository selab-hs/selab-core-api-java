package kr.ac.hs.selab.notice.converter;

import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeCreateDto;
import kr.ac.hs.selab.notice.dto.NoticeUpdateDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindAllByPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeFindResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class NoticeConverter {
    public Notice toNotice(Long memberId, NoticeCreateDto dto) {
        return Notice.builder()
                .memberId(memberId)
                .title(dto.getTitle())
                .content(dto.getContent())
                .image(dto.getImage())
                .build();
    }

    public NoticeFindResponse toNoticeFindResponse(Notice notice) {
        return NoticeFindResponse.builder()
                .noticeId(notice.getId())
                .memberId(notice.getMemberId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .modifiedAt(notice.getModifiedAt())
                .build();
    }

    public NoticeFindAllByPageResponse toNoticeFindAllByPageResponse(Long totalCount, Pageable pageable, Page<Notice> notices) {
        List<NoticeFindResponse> noticeResponses = notices.stream()
                .map(NoticeConverter::toNoticeFindResponse)
                .collect(Collectors.toList());
        return NoticeFindAllByPageResponse.builder()
                .totalCount(totalCount)
                .pageNumber(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .sort(pageable.getSort().toString())
                .notices(noticeResponses)
                .build();
    }

    public NoticeCreateDto toNoticeCreateDto(String memberEmail, NoticeRequest request) {
        return NoticeCreateDto.builder()
                .memberEmail(memberEmail)
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .build();
    }

    public NoticeUpdateDto toNoticeUpdateDto(Long id, NoticeRequest request) {
        return NoticeUpdateDto.builder()
                .id(id)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }
}