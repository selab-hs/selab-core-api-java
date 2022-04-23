package kr.ac.hs.selab.member.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.SwaggerNote;
import kr.ac.hs.selab.member.dto.request.MemberCreateRequest;
import kr.ac.hs.selab.member.dto.request.MemberExistRequest;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;
import kr.ac.hs.selab.member.dto.response.MemberExistResponse;

@Api(tags = "회원 API", description = "Member Controller (MVP)")
public interface MemberSdk {
    @ApiOperation(value = "회원가입", notes = SwaggerNote.MEMBER_CREATE)
    @ApiResponses(
            @ApiResponse(code = 201, message = "회원가입 성공")
    )
    ResponseTemplate<MemberCreateResponse> create(@Parameter(description = "회원가입 시 필요한 정보") MemberCreateRequest request);

    @ApiOperation(value = "회원가입 여부 조회", notes = SwaggerNote.MEMBER_EXIST)
    @ApiResponses(
            @ApiResponse(code = 201, message = "회원여부 조회 성공")
    )
    ResponseTemplate<MemberExistResponse> exist(@Parameter(description = "회원 가입 여부를 확인할 이메일") MemberExistRequest request);
}