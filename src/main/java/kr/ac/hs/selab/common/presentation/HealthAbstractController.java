package kr.ac.hs.selab.common.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.ac.hs.selab.common.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

@Api(tags = "Health Check API")
public abstract class HealthAbstractController {

    @ApiOperation("Health Check")
    public abstract ResponseEntity<ResponseDto<String>> check();
}