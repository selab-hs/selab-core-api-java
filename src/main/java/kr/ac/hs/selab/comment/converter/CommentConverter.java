package kr.ac.hs.selab.comment.converter;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentFindByPostIdAndPageDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostIdAndPageResponse;
import kr.ac.hs.selab.comment.dto.response.CommentFindResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@UtilityClass
public class CommentConverter {
    public CommentFindResponse toCommentResponse(Comment comment) {
        return CommentFindResponse.builder()
                .memberId(comment.getMemberId())
                .postId(comment.getPostId())
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public CommentFindByPostIdAndPageResponse toCommentsResponse(CommentFindByPostIdAndPageDto dto, Long totalCount, Page<Comment> comments) {
        return CommentFindByPostIdAndPageResponse.builder()
                .postId(dto.getPostId())
                .totalCount(totalCount)
                .pageNumber(dto.getPageable().getPageNumber())
                .pageSize(dto.getPageable().getPageSize())
                .sort(dto.getPageable().getSort().toString())
                .comments(
                        comments.stream()
                                .map(CommentConverter::toCommentInnerResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private CommentFindByPostIdAndPageResponse.CommentInnerResponse toCommentInnerResponse(Comment comment) {
        return CommentFindByPostIdAndPageResponse.CommentInnerResponse
                .builder()
                .memberId(comment.getMemberId())
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public CommentCreateDto toCommentCreateDto(CommentRequest request, Long postId, String memberEmail) {
        return CommentCreateDto.builder()
                .memberEmail(memberEmail)
                .postId(postId)
                .content(request.getContent())
                .build();
    }

    public CommentUpdateDto toCommentUpdateDto(Long commentId, CommentRequest request) {
        return new CommentUpdateDto(commentId, request.getContent());
    }
}
