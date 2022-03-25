package kr.ac.hs.selab.notice.converter;

import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeCreateDto;
import kr.ac.hs.selab.notice.dto.NoticeFindAllByPageDto;
import kr.ac.hs.selab.notice.dto.NoticeUpdateDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindAllByPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeFindResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class NoticeConverter {
    public Notice toNotice(NoticeCreateDto dto) {
        return Notice.builder()
                .memberId(dto.getMemberId())
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
                .image(notice.getImage())
                .createdAt(notice.getCreatedAt())
                .modifiedAt(notice.getModifiedAt())
                .build();
    }

    public NoticeFindAllByPageResponse toNoticeFindAllByPageResponse(NoticeFindAllByPageDto noticeFindAllByPageDto) {
        List<NoticeFindResponse> noticeResponses = noticeFindAllByPageDto.getNotices()
                .stream()
                .map(NoticeConverter::toNoticeFindResponse)
                .collect(Collectors.toList());
        return NoticeFindAllByPageResponse.builder()
                .totalCount(noticeFindAllByPageDto.getTotalCount())
                .pageNumber(noticeFindAllByPageDto.getPageable().getPageNumber())
                .pageSize(noticeFindAllByPageDto.getPageable().getPageSize())
                .sort(noticeFindAllByPageDto.getPageable().getSort().toString())
                .notices(noticeResponses)
                .build();
    }

    public NoticeCreateDto toNoticeCreateDto(Long memberId, NoticeRequest request) {
        return NoticeCreateDto.builder()
                .memberId(memberId)
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
                .image(request.getImage())
                .build();
    }
}