package kr.ac.hs.selab.notice.converter;

import kr.ac.hs.selab.notice.domain.Notice;
import kr.ac.hs.selab.notice.dto.NoticeCreateDto;
import kr.ac.hs.selab.notice.dto.NoticeFindByPageDto;
import kr.ac.hs.selab.notice.dto.NoticeUpdateDto;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindByIdResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeFindByPageResponse;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class NoticeConverter {
    public Notice toNotice(NoticeCreateDto dto) {
        return Notice.builder()
                .memberId(dto.getMemberId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public NoticeFindByIdResponse toNoticeFindByIdResponse(Notice notice) {
        return NoticeFindByIdResponse.builder()
                .noticeId(notice.getId())
                .memberId(notice.getMemberId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .modifiedAt(notice.getModifiedAt())
                .build();
    }

    public NoticeFindByPageResponse toNoticeFindByPageResponse(NoticeFindByPageDto dto) {
        var responses = dto.getNotices()
                .stream()
                .map(NoticeConverter::toNoticeFindByIdResponse)
                .collect(Collectors.toList());

        return NoticeFindByPageResponse.builder()
                .totalCount(dto.getTotalCount())
                .pageNumber(dto.getPageable().getPageNumber())
                .pageSize(dto.getPageable().getPageSize())
                .sort(dto.getPageable().getSort().toString())
                .notices(responses)
                .build();
    }

    public NoticeCreateDto toNoticeCreateDto(Long memberId, NoticeRequest request) {
        return NoticeCreateDto.builder()
                .memberId(memberId)
                .title(request.getTitle())
                .content(request.getContent())
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