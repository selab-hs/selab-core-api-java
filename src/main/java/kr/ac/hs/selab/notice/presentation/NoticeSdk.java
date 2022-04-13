package kr.ac.hs.selab.notice.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.SwaggerNote;
import kr.ac.hs.selab.notice.dto.request.NoticeRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeFindByPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeFindByIdResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "공지사항 관리")
public interface NoticeSdk {
    @ApiOperation(value = "공지사항 생성", notes = SwaggerNote.NOTICE_CREATE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 생성 성공"),
            @ApiResponse(code = 400, message = "공지사항 생성 실패")
    })
    ResponseTemplate<NoticeResponse> create(@Parameter(description = "공지사항 정보") NoticeRequest request);

    @ApiOperation(value = "공지사항 조회", notes = SwaggerNote.NOTICE_FIND)
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 조회 실패")
    })
    ResponseTemplate<NoticeFindByIdResponse> findById(@Parameter(description = "공지사항 id 값") Long id);

    @ApiOperation(value = "전체 공지사항 조회", notes = SwaggerNote.NOTICE_FIND_BY_PAGE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 조회 실패")
    })
    ResponseTemplate<NoticeFindByPageResponse> findByPage(@Parameter(description = "페이지 정보") Pageable pageable);

    @ApiOperation(value = "공지사항 수정", notes = SwaggerNote.NOTICE_UPDATE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 수정 성공"),
            @ApiResponse(code = 400, message = "공지사항 수정 실패")
    })
    ResponseTemplate<NoticeResponse> update(@Parameter(description = "공지사항 id 값") Long id,
                                            @Parameter(description = "공지사항 정보") NoticeRequest request);

    @ApiOperation(value = "공지사항 삭제", notes = SwaggerNote.NOTICE_DELETE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 삭제 성공"),
            @ApiResponse(code = 400, message = "공지사항 삭제 실패")
    })
    ResponseTemplate<NoticeResponse> delete(@Parameter(description = "공지사항 id 값") Long id);
}
