package kr.ac.hs.selab.board.converter;

import kr.ac.hs.selab.board.domain.Comment;
import kr.ac.hs.selab.board.dto.CommentCreateDto;
import kr.ac.hs.selab.board.dto.CommentFindByPostIdAndPageDto;
import kr.ac.hs.selab.board.dto.CommentUpdateDto;
import kr.ac.hs.selab.board.dto.request.CommentRequest;
import kr.ac.hs.selab.board.dto.response.CommentFindByPostIdAndPageResponse;
import kr.ac.hs.selab.board.dto.response.CommentFindResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@UtilityClass
public class CommentConverter {
    public CommentFindResponse toCommentFindResponse(Comment comment) {
        return CommentFindResponse.builder()
                .memberId(comment.getMemberId())
                .postId(comment.getPostId())
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public CommentFindByPostIdAndPageResponse toCommentFindByPostIdAndPageResponse(CommentFindByPostIdAndPageDto dto, Long totalCount, Page<Comment> comments) {
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
