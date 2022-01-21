package kr.ac.hs.selab.member.presentation;

import javax.validation.Valid;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.dto.bundle.MemberCreateBundle;
import kr.ac.hs.selab.member.dto.request.MemberCreateRequest;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/members")
@RequiredArgsConstructor
public class MemberController implements MemberSdk {

    private final MemberService memberService;

    // TODO : @Valid와 @Validated 학습 진행하기
    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTemplate<MemberCreateResponse> insert(
        @Valid @RequestBody MemberCreateRequest request) {
        MemberCreateBundle bundle = MemberConverter.toCreateMemberBundle(request);
        return memberService.create(bundle);
    }
}