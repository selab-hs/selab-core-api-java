package kr.ac.hs.selab.free_post_comment.converter;

import kr.ac.hs.selab.free_post_comment.domain.FreePostComment;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentCreateDto;
import kr.ac.hs.selab.free_post_comment.dto.FreePostCommentFindByFreePostIdAndPageDto;
import kr.ac.hs.selab.free_post_comment.dto.response.FreePostCommentFindByFreePostIdAndPageResponse;
import kr.ac.hs.selab.free_post_comment.dto.response.FreePostCommentFindResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@UtilityClass
public class FreePostCommentConverter {
    public FreePostComment toFreePostComment(FreePostCommentCreateDto dto) {
        return FreePostComment.builder()
                .freePostId(dto.getFreePostId())
                .memberId(dto.getMemberId())
                .content(dto.getFreePostCommentContent())
                .build();
    }

    public FreePostCommentFindResponse toFreePostCommentFindResponse(FreePostComment comment) {
        return FreePostCommentFindResponse.builder()
                .memberId(comment.getMemberId())
                .freePostId(comment.getFreePostId())
                .freePostCommentId(comment.getId())
                .freePostCommentContent(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public FreePostCommentFindByFreePostIdAndPageResponse toFreePostCommentFindByFreePostIdAndPageResponse(FreePostCommentFindByFreePostIdAndPageDto dto, Long totalCount, Page<FreePostComment> comments) {
        var responses = comments.stream()
                .map(FreePostCommentConverter::toNoticeCommentFindByFreePostIdAndPageResponseInnerResponse)
                .collect(Collectors.toList());

        return FreePostCommentFindByFreePostIdAndPageResponse.builder()
                .freePostId(dto.getFreePostId())
                .totalCount(totalCount)
                .pageNumber(dto.getPageable().getPageNumber())
                .pageSize(dto.getPageable().getPageSize())
                .sort(dto.getPageable().getSort().toString())
                .freePostComments(responses)
                .build();
    }

    private FreePostCommentFindByFreePostIdAndPageResponse.InnerResponse toNoticeCommentFindByFreePostIdAndPageResponseInnerResponse(FreePostComment comment) {
        return FreePostCommentFindByFreePostIdAndPageResponse.InnerResponse
                .builder()
                .memberId(comment.getMemberId())
                .freePostCommentId(comment.getId())
                .freePostCommentContent(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
