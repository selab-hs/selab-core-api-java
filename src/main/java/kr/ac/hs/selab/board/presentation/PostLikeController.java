package kr.ac.hs.selab.board.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.board.dto.PostLikeDto;
import kr.ac.hs.selab.board.dto.PostLikeFindDto;
import kr.ac.hs.selab.board.dto.response.PostLikeFindResponse;
import kr.ac.hs.selab.board.dto.response.PostLikeResponse;
import kr.ac.hs.selab.board.facade.PostLikeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostLikeController implements PostLikeSdk {
    private final PostLikeFacade postLikeFacade;

    @Override
    @PostMapping("/{postId}/likes")
    public ResponseTemplate<PostLikeResponse> create(@PathVariable Long postId) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var dto = new PostLikeDto(memberEmail, postId);

        var response = postLikeFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.POST_LIKE_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{postId}/likes")
    public ResponseTemplate<PostLikeFindResponse> find(@PathVariable Long postId) {
        var dto = new PostLikeFindDto(postId);
        var response = postLikeFacade.find(dto);

        return ResponseTemplate.ok(ResponseMessage.POST_LIKE_FIND_SUCCESS, response);
    }

    @Override
    @DeleteMapping("/likes/{id}")
    public ResponseTemplate<PostLikeResponse> delete(@PathVariable Long id) {
        var response = postLikeFacade.delete(id);
        return ResponseTemplate.ok(ResponseMessage.POST_LIKE_DELETE_SUCCESS, response);
    }
}
