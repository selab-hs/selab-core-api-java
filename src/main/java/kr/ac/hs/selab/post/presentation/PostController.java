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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
@RestController
public class PostController implements PostSdk {
    private final PostService postService;

    @Override
    @PostMapping(value = "boards/{boardId}/posts")
    public ResponseTemplate<PostResponse> create(@PathVariable Long boardId,
                                                 @Valid @RequestBody PostRequest request) {
        String memberEmail = SecurityUtils.getCurrentUsername();
        PostCreateDto dto = PostConverter.toPostCreateDto(boardId, request, memberEmail);

        PostResponse response = postService.create(dto);
        return ResponseTemplate.created(ResponseMessage.POST_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/posts/{postId}")
    public ResponseTemplate<PostResponse> find(@PathVariable Long postId) {
        PostResponse response = postService.find(postId);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping(value = "/boards/{boardId}/posts")
    public ResponseTemplate<PostsResponse> findByBoard(@PathVariable Long boardId) {
        PostsResponse response = postService.findByBoard(boardId);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/{postId}")
    public ResponseTemplate<PostResponse> update(@PathVariable Long postId,
                                                 @Valid @RequestBody PostRequest request) {
        PostUpdateDto dto = PostConverter.toPostUpdateDto(postId, request);
        PostResponse response = postService.update(dto);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @PatchMapping("/{PostId}")
    public ResponseTemplate<PostResponse> delete(@PathVariable Long PostId) {
        PostResponse response = postService.delete(PostId);
        return ResponseTemplate.ok(ResponseMessage.POST_DELETE_SUCCESS, response);
    }
}
