package kr.ac.hs.selab.free_post.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.utils.SecurityUtils;
import kr.ac.hs.selab.free_post.dto.request.FreePostRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByIdResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByPageResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostResponse;
import kr.ac.hs.selab.free_post.facade.FreePostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/free-posts")
@RestController
public class FreePostController implements FreePostSdk {
    private final FreePostFacade freePostFacade;

    @Override
    @PostMapping
    public ResponseTemplate<FreePostResponse> create(@Validated @RequestBody FreePostRequest request) {
        var memberEmail = SecurityUtils.getCurrentUsername();
        var response = freePostFacade.create(memberEmail, request);
        return ResponseTemplate.created(ResponseMessage.FREE_POST_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseTemplate<FreePostFindByIdResponse> find(@PathVariable Long id) {
        var response = freePostFacade.findById(id);
        return ResponseTemplate.ok(ResponseMessage.FREE_POST_FIND_SUCCESS, response);
    }

    @GetMapping
    public ResponseTemplate<FreePostFindByPageResponse> findByPage(@PageableDefault(size = 20, page = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var response = freePostFacade.findByPage(pageable);
        return ResponseTemplate.ok(ResponseMessage.FREE_POST_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseTemplate<FreePostResponse> update(@PathVariable Long id,
                                                     @Validated @RequestBody FreePostRequest request) {
        var response = freePostFacade.update(id, request);
        return ResponseTemplate.ok(ResponseMessage.FREE_POST_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseTemplate<FreePostResponse> delete(@PathVariable Long id) {
        var response = freePostFacade.delete(id);
        return ResponseTemplate.ok(ResponseMessage.FREE_POST_DELETE_SUCCESS, response);
    }
}