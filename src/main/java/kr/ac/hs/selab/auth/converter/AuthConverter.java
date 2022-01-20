package kr.ac.hs.selab.auth.converter;

import kr.ac.hs.selab.auth.dto.response.AuthLoginResponse;
import kr.ac.hs.selab.member.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class AuthConverter {

    public static AuthLoginResponse toAuthLoginResponse(Authentication authentication,
        String token) {
        return new AuthLoginResponse(authentication.getName(), token);
    }

    public static User toUser(Member member) {
        return new User(
            member.getEmail(),
            member.getPasswordValue(),
            member.getAuthority()
        );
    }
}