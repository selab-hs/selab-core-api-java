package kr.ac.hs.selab.notice_comment.converter;

import kr.ac.hs.selab.notice_comment.domain.NoticeComment;
import kr.ac.hs.selab.notice_comment.dto.NoticeCommentCreateDto;
import kr.ac.hs.selab.notice_comment.dto.NoticeCommentFindByNoticeIdAndPageDto;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentFindByNoticeIdAndPageResponse;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentFindResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@UtilityClass
public class NoticeCommentConverter {
    public NoticeComment toNoticeComment(NoticeCommentCreateDto dto) {
        return NoticeComment.builder()
                .noticeId(dto.getNoticeId())
                .memberId(dto.getMemberId())
                .content(dto.getNoticeCommentContent())
                .build();
    }

    public NoticeCommentFindResponse toNoticeCommentFindResponse(NoticeComment comment) {
        return NoticeCommentFindResponse.builder()
                .memberId(comment.getMemberId())
                .noticeId(comment.getNoticeId())
                .noticeCommentId(comment.getId())
                .noticeCommentContent(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public NoticeCommentFindByNoticeIdAndPageResponse toNoticeCommentFindByNoticeIdAndPageResponse(NoticeCommentFindByNoticeIdAndPageDto dto, Long totalCount, Page<NoticeComment> comments) {
        var responses = comments.stream()
                .map(NoticeCommentConverter::toNoticeCommentFindByNoticeIdAndPageResponseInnerResponse)
                .collect(Collectors.toList());

        return NoticeCommentFindByNoticeIdAndPageResponse.builder()
                .noticeId(dto.getNoticeId())
                .totalCount(totalCount)
                .pageNumber(dto.getPageable().getPageNumber())
                .pageSize(dto.getPageable().getPageSize())
                .sort(dto.getPageable().getSort().toString())
                .noticeComments(responses)
                .build();
    }

    private NoticeCommentFindByNoticeIdAndPageResponse.InnerResponse toNoticeCommentFindByNoticeIdAndPageResponseInnerResponse(NoticeComment comment) {
        return NoticeCommentFindByNoticeIdAndPageResponse.InnerResponse
                .builder()
                .memberId(comment.getMemberId())
                .noticeCommentId(comment.getId())
                .noticeCommentContent(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
