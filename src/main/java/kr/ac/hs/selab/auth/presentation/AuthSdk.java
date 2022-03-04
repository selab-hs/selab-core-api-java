package kr.ac.hs.selab.auth.presentation;


import io.swagger.annotations.*;
import kr.ac.hs.selab.auth.dto.request.AuthLoginRequest;
import kr.ac.hs.selab.auth.dto.response.AuthLoginResponse;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.SwaggerNote;

@Api(tags = "Auth REST API", description = "인증 및 인가 api")
public interface AuthSdk {

    @ApiOperation(value = "로그인", notes = SwaggerNote.AUTH_LOGIN)
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공")
    })
    ResponseTemplate<AuthLoginResponse> login(
            @ApiParam(value = "이메일과 비밀번호")
                    AuthLoginRequest request);
}