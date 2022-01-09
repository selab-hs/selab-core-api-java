package kr.ac.hs.selab.member.converter;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Avatar;
import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Name;
import kr.ac.hs.selab.member.domain.vo.Nickname;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.domain.vo.StudentId;
import kr.ac.hs.selab.member.domain.vo.Terms;
import kr.ac.hs.selab.member.dto.bundle.CreateMemberBundle;
import kr.ac.hs.selab.member.dto.request.CreateMemberRequest;
import kr.ac.hs.selab.member.dto.response.CreateMemberResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

    public CreateMemberBundle toCreateMemberBundle(CreateMemberRequest request) {
        return CreateMemberBundle.builder()
            .email(Email.of(request.getEmail()))
            .password(Password.of(request.getPassword()))
            .studentId(StudentId.of(request.getStudentId()))
            .name(Name.of(request.getName()))
            .nickname(Nickname.of(request.getNickname()))
            .avatar(Avatar.of(request.getAvatar()))
            .terms(Terms.of(request.isTermService(), request.isTermPrivacy()))
            .build();
    }

    public Member toMember(CreateMemberBundle bundle, PasswordEncoder passwordEncoder) {
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

    public CreateMemberResponse toCreateMemberResponse(Member member) {
        return new CreateMemberResponse(member.getNicknameValue());
    }
}