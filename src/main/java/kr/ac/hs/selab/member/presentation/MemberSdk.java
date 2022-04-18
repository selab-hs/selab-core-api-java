package kr.ac.hs.selab.member.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.SwaggerNote;
import kr.ac.hs.selab.member.dto.request.MemberCreateRequest;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;

@Api(tags = "회원 API", description = "Member Controller (MVP)")
public interface MemberSdk {
    @ApiOperation(value = "회원가입", notes = SwaggerNote.MEMBER_CREATE)
    @ApiResponses(
            @ApiResponse(code = 201, message = "회원가입 성공")
    )
    ResponseTemplate<MemberCreateResponse> insert(@Parameter(description = "회원가입 시 필요한 정보") MemberCreateRequest request);
}