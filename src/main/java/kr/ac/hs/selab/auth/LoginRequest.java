package kr.ac.hs.selab.auth;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String email;
    private String password;
}
