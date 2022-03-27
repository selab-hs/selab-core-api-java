package kr.ac.hs.selab.coreQa.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.coreQa.dto.request.CoreQaCreateRequest;
import kr.ac.hs.selab.coreQa.dto.response.CoreQaCreateResponse;

@Api(tags = "CoreQa REST API", description = "CORE QA api")
public interface CoreQaSdk {
    @Operation(summary = "CORE QA 생성", description = "CORE QA 정보를 생성합니다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "core qa 생성 성공"),
            @ApiResponse(code = 400, message = "core qa 생성 실패")
    })
    ResponseTemplate<CoreQaCreateResponse> create(@Parameter(description = "core qa 정보") CoreQaCreateRequest request);
}