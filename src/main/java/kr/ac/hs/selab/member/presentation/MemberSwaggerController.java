package kr.ac.hs.selab.member.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.member.dto.request.CreateMemberRequest;
import kr.ac.hs.selab.member.dto.response.CreateMemberResponse;
import org.springframework.http.ResponseEntity;

@Api(tags = "Member REST API", description = "회원 api")
public abstract class MemberSwaggerController {

    @Operation(summary = "회원가입", description = "회원정보를 이용하여 회원가입을 진행한다.")
    @ApiResponses({
        @ApiResponse(code = 201, message = "회원가입 성공"),
        @ApiResponse(code = 400, message = "중복된 값이 존재하여 회원가입 실패")
    })
    public abstract ResponseEntity<ResponseTemplate<CreateMemberResponse>> insert(
        @Parameter(description = "회원정보") CreateMemberRequest request);
}