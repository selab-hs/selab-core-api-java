package kr.ac.hs.selab.member.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.dto.bundle.MemberCreateBundle;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;
import kr.ac.hs.selab.terms.application.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final TermsService termsService;

    @Transactional
    public MemberCreateResponse sign(MemberCreateBundle bundle){
        memberService.isDuplication(bundle);
        var instance = MemberConverter.toMember(bundle, passwordEncoder);
        var member = memberService.save(instance);
        termsService.sign(member.getId());
        return MemberConverter.toCreateMemberResponse(member);
    }


}