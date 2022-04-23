package kr.ac.hs.selab.board.presentation;

import kr.ac.hs.selab.board.converter.PostConverter;
import kr.ac.hs.selab.board.dto.PostFindByBoardAndPageDto;
import kr.ac.hs.selab.board.dto.request.PostRequest;
import kr.ac.hs.selab.board.dto.response.PostFindByBoardIdAndPageResponse;
import kr.ac.hs.selab.board.dto.response.PostFindResponse;
import kr.ac.hs.selab.board.dto.response.PostResponse;
import kr.ac.hs.selab.board.facade.PostFacade;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class PostController implements PostSdk {
    private final PostFacade postFacade;

    @Override
    @PostMapping("/boards/{boardId}/posts")
    public ResponseTemplate<PostResponse> create(@PathVariable Long boardId,
                                                 @Validated @RequestBody PostRequest request) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var dto = PostConverter.toPostCreateDto(request, boardId, memberEmail);

        var response = postFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.POST_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/posts/{postId}")
    public ResponseTemplate<PostFindResponse> find(@PathVariable Long postId) {
        var response = postFacade.findPostResponseById(postId);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping("/boards/{boardId}/posts")
    public ResponseTemplate<PostFindByBoardIdAndPageResponse> findByBoardAndPage(@PathVariable Long boardId,
                                                                                 @PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var dto = new PostFindByBoardAndPageDto(boardId, pageable);
        var response = postFacade.findPostsResponseByBoardId(dto);
        return ResponseTemplate.ok(ResponseMessage.POST_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/posts/{id}")
    public ResponseTemplate<PostResponse> update(@PathVariable Long id,
                                                 @Validated @RequestBody PostRequest request) {
        var dto = PostConverter.toPostUpdateDto(id, request);
        var response = postFacade.update(dto);
        return ResponseTemplate.ok(ResponseMessage.POST_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/posts/{id}")
    public ResponseTemplate<PostResponse> delete(@PathVariable Long id) {
        var response = postFacade.delete(id);
        return ResponseTemplate.ok(ResponseMessage.POST_DELETE_SUCCESS, response);
    }
}
