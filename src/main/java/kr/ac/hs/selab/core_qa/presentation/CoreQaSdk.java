package kr.ac.hs.selab.core_qa.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.PageResponseTemplate;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.core_qa.dto.request.CoreQaCreateRequest;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaCreateResponse;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaReadResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "코어 질의 응답 API", description = "Core Qa Controller (MVP)")
public interface CoreQaSdk {
    @ApiOperation(value = "CORE QA 생성", notes = "CORE QA 정보를 생성합니다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "core qa 생성 성공"),
            @ApiResponse(code = 400, message = "core qa 생성 실패")
    })
    ResponseTemplate<CoreQaCreateResponse> create(@Parameter(description = "core qa 정보") CoreQaCreateRequest request);

    @ApiOperation(value = "CORE QA 전체 조회", notes = "CORE QA 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "core qa 전체 조회 성공"),
            @ApiResponse(code = 400, message = "core qa 전체 조회 실패")
    })
    PageResponseTemplate<CoreQaReadResponse> findCoreQaAll(Pageable pageable);

    @ApiOperation(value = "CORE QA 조회", notes = "어쩌구 저쩌구")
    @ApiResponses({
            @ApiResponse(code = 201, message = "core qa 생성 성공"),
            @ApiResponse(code = 400, message = "core qa 생성 실패")
    })
    ResponseTemplate<CoreQaReadResponse> findCoreQa(@Parameter Long id);
}