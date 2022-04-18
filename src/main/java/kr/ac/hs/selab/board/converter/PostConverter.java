package kr.ac.hs.selab.board.converter;

import kr.ac.hs.selab.board.domain.Post;
import kr.ac.hs.selab.board.dto.PostCreateDto;
import kr.ac.hs.selab.board.dto.PostFindByBoardAndPageDto;
import kr.ac.hs.selab.board.dto.PostUpdateDto;
import kr.ac.hs.selab.board.dto.request.PostRequest;
import kr.ac.hs.selab.board.dto.response.PostFindByBoardIdAndPageResponse;
import kr.ac.hs.selab.board.dto.response.PostFindResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@UtilityClass
public class PostConverter {
    public Post toPost(PostCreateDto dto, Long memberId, Long boardId) {
        return Post.builder()
                .memberId(memberId)
                .boardId(boardId)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public PostFindResponse toPostFindResponse(Post post) {
        return PostFindResponse.builder()
                .boardId(post.getId())
                .memberId(post.getMemberId())
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }

    public PostFindByBoardIdAndPageResponse toPostFindByBoardResponse(PostFindByBoardAndPageDto dto, Long totalCount, Page<Post> posts) {
        return PostFindByBoardIdAndPageResponse.builder()
                .boardId(dto.getBoardId())
                .totalCount(totalCount)
                .pageNumber(dto.getPageable().getPageNumber())
                .pageSize(dto.getPageable().getPageSize())
                .sort(dto.getPageable().getSort().toString())
                .posts(
                        posts.stream()
                                .map(PostConverter::toPostInnerResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private PostFindByBoardIdAndPageResponse.PostInnerResponse toPostInnerResponse(Post post) {
        return PostFindByBoardIdAndPageResponse.PostInnerResponse
                .builder()
                .memberId(post.getMemberId())
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

    public PostUpdateDto toPostUpdateDto(Long postId, PostRequest request) {
        return PostUpdateDto.builder()
                .id(postId)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }
}
