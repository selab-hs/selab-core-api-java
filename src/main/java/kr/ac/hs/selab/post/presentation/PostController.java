package kr.ac.hs.selab.post.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostFindByBoardAndPageDto;
import kr.ac.hs.selab.post.dto.PostUpdateDto;
import kr.ac.hs.selab.post.dto.request.PostRequest;
import kr.ac.hs.selab.post.dto.response.PostFindByBoardAndPageResponse;
import kr.ac.hs.selab.post.dto.response.PostFindResponse;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import kr.ac.hs.selab.post.facade.PostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("api/v1")
@RestController
public class PostController implements PostSdk {
    private final PostService postService;
    private final PostFacade postFacade;

    @Override
    @PostMapping("/boards/{boardId}/posts")
    public ResponseTemplate<PostResponse> create(@PathVariable Long boardId,
                                                 @Valid @RequestBody PostRequest request) {
        String memberEmail = SecurityUtils.getCurrentUsername();
        PostCreateDto dto = PostConverter.toPostCreateDto(request, boardId, memberEmail);

        PostResponse response = postFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.POST_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/posts/{postId}")
    public ResponseTemplate<PostFindResponse> find(@PathVariable Long postId) {
        PostFindResponse response = postService.findPostResponseById(postId);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping("/boards/{boardId}/posts")
    public ResponseTemplate<PostFindByBoardAndPageResponse> findByBoardAndPage(@PathVariable Long boardId,
                                                                               @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        PostFindByBoardAndPageDto dto = new PostFindByBoardAndPageDto(boardId, pageable);
        PostFindByBoardAndPageResponse response = postFacade.findPostsResponseByBoardId(dto);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/posts/{id}")
    public ResponseTemplate<PostResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody PostRequest request) {
        PostUpdateDto dto = PostConverter.toPostUpdateDto(id, request);
        PostResponse response = postService.update(dto);
        return ResponseTemplate.ok(ResponseMessage.POST_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/posts/{id}")
    public ResponseTemplate<PostResponse> delete(@PathVariable Long id) {
        PostResponse response = postFacade.delete(id);
        return ResponseTemplate.ok(ResponseMessage.POST_DELETE_SUCCESS, response);
    }
}
