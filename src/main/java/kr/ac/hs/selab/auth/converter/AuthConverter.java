package kr.ac.hs.selab.auth.converter;

import kr.ac.hs.selab.auth.dto.response.AuthLoginResponse;
import kr.ac.hs.selab.member.domain.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthConverter {

    public static AuthLoginResponse toAuthLoginResponse(Authentication authentication,
                                                        String token) {
        return new AuthLoginResponse(authentication.getName(), token);
    }

    public static UserDetails toUser(Member member) {
        return User.builder()
                .username(member.getEmail())
                .password(member.getPasswordValue())
                .authorities(member.getAuthority())
                .build();
    }
}