package kr.ac.hs.selab.free_post.facade;

import kr.ac.hs.selab.free_post.application.FreePostService;
import kr.ac.hs.selab.free_post.converter.FreePostConverter;
import kr.ac.hs.selab.free_post.dto.FreePostFindByPageDto;
import kr.ac.hs.selab.free_post.dto.request.FreePostRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByPageResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByIdResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostResponse;
import kr.ac.hs.selab.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class FreePostFacade {
    private final FreePostService freePostService;
    private final MemberService memberService;

    @Transactional
    public FreePostResponse create(String memberEmail, FreePostRequest request) {
        var memberId = memberService.findByEmail(memberEmail).getId();

        var dto = FreePostConverter.toFreePostCreateDto(memberId, request);
        var post = freePostService.create(dto);

        return new FreePostResponse(post.getId());
    }

    @Transactional
    public FreePostFindByIdResponse findById(Long id) {
        var post = freePostService.findById(id);
        return FreePostConverter.toFreePostFindByIdResponse(post);
    }

    public FreePostFindByPageResponse findByPage(Pageable pageable) {
        var dto = FreePostFindByPageDto.builder()
                .totalCount(freePostService.count())
                .pageable(pageable)
                .freePosts(freePostService.findByPage(pageable))
                .build();
        return FreePostConverter.toFreePostFindByPageResponse(dto);
    }

    @Transactional
    public FreePostResponse update(Long id, FreePostRequest request) {
        var dto = FreePostConverter.toFreePostUpdateDto(id, request);
        var post = freePostService.update(dto);
        return new FreePostResponse(post.getId());
    }

    @Transactional
    public FreePostResponse delete(Long id) {
        var post = freePostService.delete(id);
        return new FreePostResponse(post.getId());
    }
}
