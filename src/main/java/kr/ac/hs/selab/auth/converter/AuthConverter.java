package kr.ac.hs.selab.auth.converter;

import kr.ac.hs.selab.auth.dto.response.LoginResponse;
import kr.ac.hs.selab.member.domain.Member;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {

    public LoginResponse toLoginResponse(Member member, String token) {
        return LoginResponse.builder()
            .nickname(member.getNicknameValue())
            .role(member.getRoleValue())
            .token(token)
            .build();
    }

    public User toUser(Member member) {
        return new User(
            member.getEmailValue(),
            member.getPasswordValue(),
            member.getAuthority()
        );
    }
}