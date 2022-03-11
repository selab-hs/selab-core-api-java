package kr.ac.hs.selab.postLike.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.postLike.application.PostLikeService;
import kr.ac.hs.selab.postLike.dto.PostLikeDto;
import kr.ac.hs.selab.postLike.dto.PostLikeFindDto;
import kr.ac.hs.selab.postLike.dto.response.PostLikeFindResponse;
import kr.ac.hs.selab.postLike.dto.response.PostLikeResponse;
import kr.ac.hs.selab.postLike.facade.PostLikeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
@RestController
public class PostLikeController implements PostLikeSdk {
    private final PostLikeService postLikeService;
    private final PostLikeFacade postLikeFacade;

    @Override
    @PostMapping("/{postId}/likes")
    public ResponseTemplate<PostLikeResponse> create(@PathVariable Long postId) {
        String memberEmail = SecurityUtils.getCurrentUsername();
        PostLikeDto dto = new PostLikeDto(memberEmail, postId);

        PostLikeResponse response = postLikeFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.POST_LIKE_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{postId}/likes")
    public ResponseTemplate<PostLikeFindResponse> find(@PathVariable Long postId) {
        PostLikeFindDto dto = new PostLikeFindDto(postId);
        PostLikeFindResponse response = postLikeFacade.find(dto);

        return ResponseTemplate.ok(ResponseMessage.POST_LIKE_FIND_SUCCESS, response);
    }

    @Override
    @DeleteMapping("/likes/{id}")
    public ResponseTemplate<PostLikeResponse> delete(@PathVariable Long id) {
        PostLikeResponse response = postLikeService.delete(id);
        return ResponseTemplate.ok(ResponseMessage.POST_LIKE_DELETE_SUCCESS, response);
    }
}
