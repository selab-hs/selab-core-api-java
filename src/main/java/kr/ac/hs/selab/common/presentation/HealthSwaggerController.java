package kr.ac.hs.selab.common.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;

@Api(tags = "Health Check API", description = "헬스 체크 api")
public abstract class HealthSwaggerController {

    @Operation(summary = "Health Check", description = "서버의 상태를 체크합니다.")
    public abstract ResponseEntity<ResponseTemplate<String>> check();
}