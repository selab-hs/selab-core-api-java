package kr.ac.hs.selab.member.presentation;

import kr.ac.hs.selab.common.dto.ResponseTemplate;
import kr.ac.hs.selab.common.dto.ResponseMessage;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.dto.bundle.CreateMemberBundle;
import kr.ac.hs.selab.member.dto.request.CreateMemberRequest;
import kr.ac.hs.selab.member.dto.response.CreateMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/members", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController extends MemberSwaggerController {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @Override
    @PostMapping
    public ResponseEntity<ResponseTemplate<CreateMemberResponse>> insert(
        @RequestBody CreateMemberRequest request) {
        CreateMemberBundle bundle = memberConverter.toCreateMemberBundle(request);
        CreateMemberResponse response = memberService.create(bundle);
        return ResponseTemplate.of(ResponseMessage.MEMBER_INSERT_SUCCESS, response);
    }
}