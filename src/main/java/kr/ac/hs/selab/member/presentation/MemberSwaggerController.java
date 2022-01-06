package kr.ac.hs.selab.member.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.ac.hs.selab.common.dto.ResponseTemplate;
import kr.ac.hs.selab.member.dto.request.CreateMemberRequest;
import kr.ac.hs.selab.member.dto.response.CreateMemberResponse;
import org.springframework.http.ResponseEntity;

@Api(tags = "Member REST API")
public abstract class MemberSwaggerController {

    @ApiOperation("Member - 회원가입")
    @ApiResponses({
        @ApiResponse(code = 201, message = "회원가입 성공"),
        @ApiResponse(code = 400, message = "중복된 값이 존재하여 회원가입 실패")
    })
    public abstract ResponseEntity<ResponseTemplate<CreateMemberResponse>> insert(
        @ApiParam(value = "회원정보") CreateMemberRequest request);
}