package kr.ac.hs.selab.auth;

import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.dto.bundle.MemberLoginBundle;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {
    public LoginResponse toLoginResponse(MemberLoginBundle member, TokenBundle token) {
        return LoginResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname().getNickname())
                .role(member.getRole())
                .clubName(member.getClubName())
                .token(token.getToken())
                .build();
    }

    public User toUser(Member member) {
        return new User(
                member.getEmail().toString(),
                member.getPassword().toString(),
                member.getAuthority()
        );
    }
}