package kr.ac.hs.selab.notice.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindAllByPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeFindResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "Notice REST API", description = "공지사항 api")
public interface NoticeSdk {
    @Operation(summary = "공지사항 생성", description = "공지사항 정보를 이용해서 공지사항을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 생성 성공"),
            @ApiResponse(code = 400, message = "공지사항 생성 실패")
    })
    ResponseTemplate<NoticeResponse> create(@Parameter(description = "공지사항 정보") NoticeRequest request);

    @Operation(summary = "공지사항 조회", description = "공지사항 정보를 이용해서 공지사항을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 조회 실패")
    })
    ResponseTemplate<NoticeFindResponse> find(@Parameter(description = "공지사항 id 값") Long id);

    @Operation(summary = "전체 공지사항 조회", description = "전체 공지사항을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 조회 실패")
    })
    ResponseTemplate<NoticeFindAllByPageResponse> findAllByPage(@Parameter(description = "페이지 정보") Pageable pageable);

    @Operation(summary = "공지사항 수정", description = "새로운 공지사항 정보를 이용해서 공지사항을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 수정 성공"),
            @ApiResponse(code = 400, message = "공지사항 수정 실패")
    })
    ResponseTemplate<NoticeResponse> update(@Parameter(description = "공지사항 id 값") Long id,
                                            @Parameter(description = "공지사항 정보") NoticeRequest request);

    @Operation(summary = "공지사항 삭제", description = "공지사항을 지정하여 공지사항을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 삭제 성공"),
            @ApiResponse(code = 400, message = "공지사항 삭제 실패")
    })
    ResponseTemplate<NoticeResponse> delete(@Parameter(description = "공지사항 id 값") Long id);
}
