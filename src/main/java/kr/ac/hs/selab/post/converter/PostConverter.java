package kr.ac.hs.selab.post.converter;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostFindByBoardAndPageDto;
import kr.ac.hs.selab.post.dto.PostUpdateDto;
import kr.ac.hs.selab.post.dto.request.PostFindByBoardAndPageRequest;
import kr.ac.hs.selab.post.dto.request.PostRequest;
import kr.ac.hs.selab.post.dto.response.PostFindByBoardAndPageResponse;
import kr.ac.hs.selab.post.dto.response.PostFindResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

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

    public PostFindByBoardAndPageResponse toPostFindByBoardResponse(PostFindByBoardAndPageDto dto, Long totalCount, Page<Post> posts) {
        return PostFindByBoardAndPageResponse.builder()
                .boardId(dto.getBoardId())
                .totalCount(totalCount)
                .page(dto.getPage())
                .size(dto.getSize())
                .posts(
                        posts.stream()
                                .map(PostConverter::toPostInnerResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private PostFindByBoardAndPageResponse.PostInnerResponse toPostInnerResponse(Post post) {
        return PostFindByBoardAndPageResponse.PostInnerResponse
                .builder()
                .memberEmail(post.getMember().getEmail())
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
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

    public PostFindByBoardAndPageDto toPostFindByBoardAndPageDto(Long boardId, PostFindByBoardAndPageRequest request) {
        return PostFindByBoardAndPageDto.builder()
                .boardId(boardId)
                .page(request.getPage())
                .size(request.getSize())
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
