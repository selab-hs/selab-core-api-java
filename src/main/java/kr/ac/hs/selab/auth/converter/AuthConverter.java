package kr.ac.hs.selab.auth.converter;

import kr.ac.hs.selab.auth.dto.response.LoginResponse;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {

    public LoginResponse toLoginResponse(Authentication authentication, String token) {
        return new LoginResponse(authentication.getName(), token);
    }

    public User toUser(Member member) {
        return new User(
            member.getEmailValue(),
            member.getPasswordValue(),
            member.getAuthority()
        );
    }
}