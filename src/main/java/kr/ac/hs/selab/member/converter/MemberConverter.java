package kr.ac.hs.selab.member.converter;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Avatar;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.domain.vo.Terms;
import kr.ac.hs.selab.member.dto.bundle.MemberCreateBundle;
import kr.ac.hs.selab.member.dto.request.MemberCreateRequest;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberConverter {

    public static MemberCreateBundle toCreateMemberBundle(MemberCreateRequest request) {
        return MemberCreateBundle.builder()
            .email(request.getEmail())
            .password(new Password(request.getPassword()))
            .studentId(request.getStudentId())
            .name(request.getName())
            .nickname(request.getNickname())
            .avatar(new Avatar(request.getAvatar()))
            .terms(new Terms(request.isTermService(), request.isTermPrivacy()))
            .build();
    }

    public static Member toMember(MemberCreateBundle bundle, PasswordEncoder passwordEncoder) {
        return Member.builder()
            .email(bundle.getEmail())
            .password(bundle.getPassword().encode(passwordEncoder))
            .studentId(bundle.getStudentId())
            .name(bundle.getName())
            .nickname(bundle.getNickname())
            .avatar(bundle.getAvatar())
            .terms(bundle.getTerms())
            .build();
    }

    public static MemberCreateResponse toCreateMemberResponse(Member member) {
        return new MemberCreateResponse(member.getEmail());
    }
}