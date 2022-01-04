package kr.ac.hs.selab.member.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.ac.hs.selab.common.dto.ResponseDto;
import kr.ac.hs.selab.common.dto.ResponseMessage;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.dto.bundle.CreateMemberBundle;
import kr.ac.hs.selab.member.dto.request.CreateMemberRequest;
import kr.ac.hs.selab.member.dto.response.CreateMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Member V1 REST API")
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @ApiOperation("MEMBER - 회원가입")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateMemberResponse>> insert(
        @RequestBody CreateMemberRequest request) {
        CreateMemberBundle bundle = memberConverter.toCreateMemberBundle(request);
        CreateMemberResponse response = memberService.create(bundle);
        return ResponseDto.of(ResponseMessage.MEMBER_INSERT_SUCCESS, response);
    }
}