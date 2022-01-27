package kr.ac.hs.selab.post.converter;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostUpdateDto;
import kr.ac.hs.selab.post.dto.request.PostRequest;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import kr.ac.hs.selab.post.dto.response.PostsResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PostConverter {
    /**
     * to entity
     */
    public Post toPost(PostCreateDto dto, Member member, Board board) {
        return Post.builder()
                .member(member)
                .board(board)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    /**
     * to Response
     */
    public PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .member(post.getMember())
                .board(post.getBoard())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }

    public PostsResponse toPostsResponse(List<Post> posts) {
        List<PostResponse> postResponses = posts.stream()
                .map(PostConverter::toPostResponse)
                .collect(Collectors.toList());
        return PostsResponse.of(postResponses);
    }

    /**
     * to dto
     */
    public static PostCreateDto toPostCreateDto(Long boardId, PostRequest request, String memberEmail) {
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
