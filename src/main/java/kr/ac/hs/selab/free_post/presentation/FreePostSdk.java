package kr.ac.hs.selab.free_post.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.SwaggerNote;
import kr.ac.hs.selab.free_post.dto.request.FreePostRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByIdResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByPageResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "자유 게시글 API", description = "Free Post Controller (MVP)")
public interface FreePostSdk {
    @ApiOperation(value = "자유게시글 생성", notes = SwaggerNote.FREE_POST_CREATE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 생성 성공"),
            @ApiResponse(code = 400, message = "자유게시글 생성 실패")
    })
    ResponseTemplate<FreePostResponse> create(@Parameter(description = "자유게시글 정보") FreePostRequest request);

    @ApiOperation(value = "자유게시글 조회", notes = SwaggerNote.FREE_POST_FIND)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 조회 성공"),
            @ApiResponse(code = 400, message = "자유게시글 조회 실패")
    })
    ResponseTemplate<FreePostFindByIdResponse> find(@Parameter(description = "자유게시글 id 값") Long id);

    @ApiOperation(value = "전체 자유게시글 조회", notes = SwaggerNote.FREE_POST_FIND_BY_PAGE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 조회 성공"),
            @ApiResponse(code = 400, message = "자유게시글 조회 실패")
    })
    ResponseTemplate<FreePostFindByPageResponse> findByPage(@Parameter(description = "페이지 정보") Pageable pageable);

    @ApiOperation(value = "자유게시글 수정", notes = SwaggerNote.FREE_POST_UPDATE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 수정 성공"),
            @ApiResponse(code = 400, message = "자유게시글 수정 실패")
    })
    ResponseTemplate<FreePostResponse> update(@Parameter(description = "자유게시글 id 값") Long id,
                                              @Parameter(description = "자유게시글 정보") FreePostRequest request);

    @ApiOperation(value = "자유게시글 삭제", notes = SwaggerNote.FREE_POST_DELETE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 삭제 성공"),
            @ApiResponse(code = 400, message = "자유게시글 삭제 실패")
    })
    ResponseTemplate<FreePostResponse> delete(@Parameter(description = "자유게시글 id 값") Long id);
}