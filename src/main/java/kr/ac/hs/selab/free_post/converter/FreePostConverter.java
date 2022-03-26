package kr.ac.hs.selab.free_post.converter;

import kr.ac.hs.selab.free_post.domain.FreePost;
import kr.ac.hs.selab.free_post.dto.FreePostCreateDto;
import kr.ac.hs.selab.free_post.dto.FreePostFindByPageDto;
import kr.ac.hs.selab.free_post.dto.FreePostUpdateDto;
import kr.ac.hs.selab.free_post.dto.request.FreePostRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByPageResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByIdResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class FreePostConverter {
    public FreePost toFreePost(FreePostCreateDto dto) {
        return FreePost.builder()
                .memberId(dto.getMemberId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public FreePostFindByIdResponse toFreePostFindByIdResponse(FreePost notice) {
        return FreePostFindByIdResponse.builder()
                .freePostId(notice.getId())
                .memberId(notice.getMemberId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .modifiedAt(notice.getModifiedAt())
                .build();
    }

    public FreePostFindByPageResponse toFreePostFindByPageResponse(FreePostFindByPageDto freePostFindAllByPageDto) {
        List<FreePostFindByIdResponse> freePostResponses = freePostFindAllByPageDto.getFreePosts()
                .stream()
                .map(FreePostConverter::toFreePostFindByIdResponse)
                .collect(Collectors.toList());
        return FreePostFindByPageResponse.builder()
                .totalCount(freePostFindAllByPageDto.getTotalCount())
                .pageNumber(freePostFindAllByPageDto.getPageable().getPageNumber())
                .pageSize(freePostFindAllByPageDto.getPageable().getPageSize())
                .sort(freePostFindAllByPageDto.getPageable().getSort().toString())
                .freePosts(freePostResponses)
                .build();
    }

    public FreePostCreateDto toFreePostCreateDto(Long memberId, FreePostRequest request) {
        return FreePostCreateDto.builder()
                .memberId(memberId)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public FreePostUpdateDto toFreePostUpdateDto(Long id, FreePostRequest request) {
        return FreePostUpdateDto.builder()
                .id(id)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }
}