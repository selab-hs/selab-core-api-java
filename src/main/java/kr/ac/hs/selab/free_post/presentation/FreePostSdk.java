package kr.ac.hs.selab.free_post.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.free_post.dto.request.FreePostRequest;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByPageResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostFindByIdResponse;
import kr.ac.hs.selab.free_post.dto.response.FreePostResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "Free Post REST API", description = "자유게시글 api")
public interface FreePostSdk {
    @Operation(summary = "자유게시글 생성", description = "자유게시글 정보를 이용해서 자유게시글을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 생성 성공"),
            @ApiResponse(code = 400, message = "자유게시글 생성 실패")
    })
    ResponseTemplate<FreePostResponse> create(@Parameter(description = "자유게시글 정보") FreePostRequest request);

    @Operation(summary = "자유게시글 조회", description = "자유게시글 정보를 이용해서 자유게시글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 조회 성공"),
            @ApiResponse(code = 400, message = "자유게시글 조회 실패")
    })
    ResponseTemplate<FreePostFindByIdResponse> find(@Parameter(description = "자유게시글 id 값") Long id);

    @Operation(summary = "전체 자유게시글 조회", description = "전체 자유게시글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 조회 성공"),
            @ApiResponse(code = 400, message = "자유게시글 조회 실패")
    })
    ResponseTemplate<FreePostFindByPageResponse> findByPage(@Parameter(description = "페이지 정보") Pageable pageable);

    @Operation(summary = "자유게시글 수정", description = "새로운 자유게시글 정보를 이용해서 자유게시글을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 수정 성공"),
            @ApiResponse(code = 400, message = "자유게시글 수정 실패")
    })
    ResponseTemplate<FreePostResponse> update(@Parameter(description = "자유게시글 id 값") Long id,
                                              @Parameter(description = "자유게시글 정보") FreePostRequest request);

    @Operation(summary = "자유게시글 삭제", description = "자유게시글을 지정하여 자유게시글을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "자유게시글 삭제 성공"),
            @ApiResponse(code = 400, message = "자유게시글 삭제 실패")
    })
    ResponseTemplate<FreePostResponse> delete(@Parameter(description = "자유게시글 id 값") Long id);
}
