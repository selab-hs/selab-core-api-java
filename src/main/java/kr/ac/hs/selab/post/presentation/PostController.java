package kr.ac.hs.selab.post.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostUpdateDto;
import kr.ac.hs.selab.post.dto.request.PostRequest;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import kr.ac.hs.selab.post.dto.response.PostsResponse;
import kr.ac.hs.selab.post.facade.PostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
@RestController
public class PostController implements PostSdk {
    private final PostService postService;
    private final PostFacade postFacade;

    @Override
    @PostMapping(value = "boards/{boardId}/posts")
    public ResponseTemplate<PostResponse> create(@PathVariable Long boardId,
                                                 @Valid @RequestBody PostRequest request) {
        String memberEmail = SecurityUtils.getCurrentUsername();
        PostCreateDto dto = PostConverter.toPostCreateDto(request, boardId, memberEmail);

        PostResponse response = postFacade.createByPostCreateDto(dto);
        return ResponseTemplate.created(ResponseMessage.POST_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/posts/{postId}")
    public ResponseTemplate<PostResponse> find(@PathVariable Long postId) {
        PostResponse response = postService.findPostResponseById(postId);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping(value = "/boards/{boardId}/posts")
    public ResponseTemplate<PostsResponse> findByBoard(@PathVariable Long boardId) {
        PostsResponse response = postFacade.findPostsResponseByBoardId(boardId);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/posts/{postId}")
    public ResponseTemplate<PostResponse> update(@PathVariable Long postId,
                                                 @Valid @RequestBody PostRequest request) {
        PostUpdateDto dto = PostConverter.toPostUpdateDto(postId, request);

        PostResponse response = postService.updateByPostUpdateDto(dto);
        return ResponseTemplate.ok(ResponseMessage.POST_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/posts/{PostId}")
    public ResponseTemplate<PostResponse> delete(@PathVariable Long PostId) {
        PostResponse response = postService.deleteById(PostId);
        return ResponseTemplate.ok(ResponseMessage.POST_DELETE_SUCCESS, response);
    }
}
