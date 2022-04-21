package kr.ac.hs.selab.member.presentation;

import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.dto.request.MemberExistRequest;
import kr.ac.hs.selab.member.dto.request.MemberCreateRequest;
import kr.ac.hs.selab.member.dto.response.MemberExistResponse;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;
import kr.ac.hs.selab.member.facade.MemberFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/members")
public class MemberController implements MemberSdk {
    private final MemberFacade memberFacade;

    @Override
    @PostMapping(value = "/sign", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTemplate<MemberCreateResponse> create(@Validated @RequestBody MemberCreateRequest request) {
        final var response = memberFacade.sign(request);
        return ResponseTemplate.created(ResponseMessage.MEMBER_CREATE_SUCCESS, response);
    }

    @Override
    @PostMapping(value = "/exist")
    public ResponseTemplate<MemberExistResponse> exist(@Validated @RequestBody MemberExistRequest request) {
        var response = memberFacade.exist(request);
        return ResponseTemplate.ok(ResponseMessage.MEMBER_CHECK_SUCCESS, response);
    }
}