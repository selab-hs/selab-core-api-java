package kr.ac.hs.selab.comment.converter;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentFindByPostAndPageDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostAndPageResponse;
import kr.ac.hs.selab.comment.dto.response.CommentFindResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@UtilityClass
public class CommentConverter {
    public CommentFindResponse toCommentResponse(Comment comment) {
        return CommentFindResponse.builder()
                .memberEmail(comment.getMember().getEmail())
                .postId(comment.getPost().getId())
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public CommentFindByPostAndPageResponse toCommentsResponse(CommentFindByPostAndPageDto dto, Long totalCount, Page<Comment> comments) {
        return CommentFindByPostAndPageResponse.builder()
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

    private CommentFindByPostAndPageResponse.CommentInnerResponse toCommentInnerResponse(Comment comment) {
        return CommentFindByPostAndPageResponse.CommentInnerResponse
                .builder()
                .memberEmail(comment.getMember().getEmail())
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
