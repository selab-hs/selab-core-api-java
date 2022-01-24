package kr.ac.hs.selab.auth.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthLoginResponse {

    private final String email;
    private final String token;
}