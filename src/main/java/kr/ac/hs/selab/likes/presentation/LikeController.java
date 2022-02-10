package kr.ac.hs.selab.likes.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.likes.application.LikeService;
import kr.ac.hs.selab.likes.domain.vo.TargetType;
import kr.ac.hs.selab.likes.dto.LikeCountDto;
import kr.ac.hs.selab.likes.dto.LikeCreateDto;
import kr.ac.hs.selab.likes.dto.response.LikeResponse;
import kr.ac.hs.selab.likes.facade.LikeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/likes/")
@RestController
public class LikeController {
    private final LikeService likeService;
    private final LikeFacade likeFacade;

    @PostMapping("{targetType}/{targetId}")
    public ResponseTemplate<String> create(@PathVariable String targetType,
                                           @PathVariable Long targetId) {
        String memberEmail = SecurityUtils.getCurrentUsername();
        LikeCreateDto dto = LikeCreateDto.builder()
                .memberEmail(memberEmail)
                .targetType(TargetType.of(targetType))
                .targetId(targetId)
                .build();

        likeFacade.createByLikeCreateDto(dto);
        return ResponseTemplate.created(ResponseMessage.LIKE_CREATE_SUCCESS, "cool");
    }

    @GetMapping("{targetType}/{targetId}")
    public ResponseTemplate<LikeResponse> count(@PathVariable String targetType,
                                                @PathVariable Long targetId) {
        LikeCountDto dto = new LikeCountDto(TargetType.of(targetType), targetId);
        LikeResponse response = likeService.findLikeResponseByLikeCountDto(dto);

        return ResponseTemplate.created(ResponseMessage.LIKE_FIND_SUCCESS, response);
    }

    @DeleteMapping("{id}")
    public ResponseTemplate<String> delete(@PathVariable Long id) {
        likeService.deleteById(id);
        return ResponseTemplate.ok(ResponseMessage.LIKE_DELETE_SUCCESS, "cool");
    }
}
