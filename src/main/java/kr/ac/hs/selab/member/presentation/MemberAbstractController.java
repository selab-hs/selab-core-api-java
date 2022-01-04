package kr.ac.hs.selab.member.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.ac.hs.selab.common.dto.ResponseDto;
import kr.ac.hs.selab.member.dto.request.CreateMemberRequest;
import kr.ac.hs.selab.member.dto.response.CreateMemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "Member REST API")
public abstract class MemberAbstractController {

    @ApiOperation("Member - 회원가입")
    public abstract ResponseEntity<ResponseDto<CreateMemberResponse>> insert(
        @RequestBody CreateMemberRequest request);
}