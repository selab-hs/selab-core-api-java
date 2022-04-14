package kr.ac.hs.selab.core_qa.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.PageResponseTemplate;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.core_qa.dto.request.CoreQaCreateRequest;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaCreateResponse;
import kr.ac.hs.selab.core_qa.dto.response.CoreQaReadResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "CoreQa REST API", description = "CORE QA api")
public interface CoreQaSdk {
    @Operation(summary = "CORE QA 생성", description = "CORE QA 정보를 생성합니다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "core qa 생성 성공"),
            @ApiResponse(code = 400, message = "core qa 생성 실패")
    })
    ResponseTemplate<CoreQaCreateResponse> create(@Parameter(description = "core qa 정보") CoreQaCreateRequest request);

    @Operation(summary = "CORE QA 전체 조회", description = "CORE QA 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "core qa 전체 조회 성공"),
            @ApiResponse(code = 400, message = "core qa 전체 조회 실패")
    })
    PageResponseTemplate<CoreQaReadResponse> getCoreQaAll(Pageable pageable);
}