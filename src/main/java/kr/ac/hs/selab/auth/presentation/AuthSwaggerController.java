package kr.ac.hs.selab.auth.presentation;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.ac.hs.selab.auth.dto.request.LoginRequest;
import kr.ac.hs.selab.auth.dto.response.LoginResponse;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;

@Api(tags = "Auth REST API")
public abstract class AuthSwaggerController {

    @ApiOperation("Auth - 로그인")
    @ApiResponses({
        @ApiResponse(code = 200, message = "로그인 성공")
    })
    public abstract ResponseEntity<ResponseTemplate<LoginResponse>> login(
        @ApiParam(value = "이메일과 비밀번호")
            LoginRequest request);
}
