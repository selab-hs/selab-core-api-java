package kr.ac.hs.selab.comment.converter;

import kr.ac.hs.selab.comment.domain.Comment;
import kr.ac.hs.selab.comment.dto.CommentCreateDto;
import kr.ac.hs.selab.comment.dto.CommentUpdateDto;
import kr.ac.hs.selab.comment.dto.request.CommentRequest;
import kr.ac.hs.selab.comment.dto.response.CommentResponse;
import kr.ac.hs.selab.comment.dto.response.CommentsResponse;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CommentConverter {
    public Comment toComment(CommentCreateDto dto, Member member, Post post) {
        return Comment.builder()
                .member(member)
                .post(post)
                .content(dto.getContent())
                .build();
    }

    public CommentResponse toCommentResponse(Comment comment) {
        MemberCreateResponse memberCreateResponse = MemberConverter.toCreateMemberResponse(comment.getMember());
        PostResponse postResponse = PostConverter.toPostResponse(comment.getPost());

        return CommentResponse.builder()
                .member(memberCreateResponse)
                .post(postResponse)
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }

    public CommentsResponse toCommentsResponse(List<Comment> comments) {
        List<CommentResponse> commentResponses = comments.stream()
                .map(CommentConverter::toCommentResponse)
                .collect(Collectors.toList());
        return new CommentsResponse(commentResponses);
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
