package kr.ac.hs.selab.auth;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Password;

public class LoginRequest {
    private Email email;
    private Password password;

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
