package kr.ac.hs.selab.member.application;

import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.dto.bundle.CreateMemberBundle;
import kr.ac.hs.selab.member.dto.response.CreateMemberResponse;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    public CreateMemberResponse create(CreateMemberBundle bundle) {
        Member instance = memberConverter.toMember(bundle);
        Member member = memberRepository.save(instance);
        return memberConverter.toCreateMemberResponse(member);
    }
}