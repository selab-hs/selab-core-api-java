package kr.ac.hs.selab.common.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import kr.ac.hs.selab.common.template.Response;
import kr.ac.hs.selab.common.template.ResponseMessage;
import org.springframework.http.HttpStatus;

@Api(tags = "Health Check API", description = "헬스 체크 api")
public interface HealthSdk {

    // TODO : 반환 값 오류 발생
    @Operation(summary = "Health Check", description = "서버의 상태를 체크합니다.")
    @ApiResponse(code = 200, message = "Health Good")
    @Response(status = HttpStatus.OK, message = ResponseMessage.HEALTH_GOOD)
    String check();
}