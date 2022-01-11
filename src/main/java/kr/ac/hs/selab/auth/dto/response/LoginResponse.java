package kr.ac.hs.selab.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {

    @Schema(description = "이메일")
    private final String email;
    @Schema(description = "토큰")
    private final String token;
}