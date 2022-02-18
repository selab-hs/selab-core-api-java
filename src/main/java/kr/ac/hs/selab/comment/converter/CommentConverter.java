package kr.ac.hs.selab.comment.converter;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentFindByPostResponse;
import kr.ac.hs.selab.comment.dto.response.CommentFindResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
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

    public CommentFindByPostResponse.CommentInnerResponse toCommentInnerResponse(Comment comment) {
        return CommentFindByPostResponse.CommentInnerResponse
                .builder()
                .memberEmail(comment.getMember().getEmail())
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public CommentFindByPostResponse toCommentsResponse(Long postId, Long totalCount, List<Comment> comments) {
        List<CommentFindByPostResponse.CommentInnerResponse> commentInnerResponses = comments.stream()
                .map(CommentConverter::toCommentInnerResponse)
                .collect(Collectors.toList());

        return CommentFindByPostResponse.builder()
                .postId(postId)
                .totalCount(totalCount)
                .comments(commentInnerResponses)
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
