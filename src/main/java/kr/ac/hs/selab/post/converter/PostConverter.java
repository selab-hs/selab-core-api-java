package kr.ac.hs.selab.post.converter;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostUpdateDto;
import kr.ac.hs.selab.post.dto.request.PostRequest;
import kr.ac.hs.selab.post.dto.response.PostFindByBoardResponse;
import kr.ac.hs.selab.post.dto.response.PostFindResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PostConverter {
    public Post toPost(PostCreateDto dto, Member member, Board board) {
        return Post.builder()
                .member(member)
                .board(board)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public PostFindResponse toPostFindResponse(Post post) {
        return PostFindResponse.builder()
                .boardId(post.getBoard().getId())
                .memberEmail(post.getMember().getEmail())
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }

    public PostFindByBoardResponse toPostFindByBoardResponse(Long boardId, Long totalCount, List<Post> posts) {
        return PostFindByBoardResponse.builder()
                .boardId(boardId)
                .totalCount(totalCount)
                .posts(
                        posts.stream()
                                .map(
                                        post -> PostFindByBoardResponse.PostInnerResponse
                                                .builder()
                                                .memberEmail(post.getMember().getEmail())
                                                .postId(post.getId())
                                                .title(post.getTitle())
                                                .content(post.getContent())
                                                .createdAt(post.getCreatedAt())
                                                .modifiedAt(post.getModifiedAt())
                                                .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }

    public PostCreateDto toPostCreateDto(PostRequest request, Long boardId, String memberEmail) {
        return PostCreateDto.builder()
                .memberEmail(memberEmail)
                .boardId(boardId)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public PostUpdateDto toPostUpdateDto(Long postId, PostRequest request) {
        return PostUpdateDto.builder()
                .id(postId)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }
}
