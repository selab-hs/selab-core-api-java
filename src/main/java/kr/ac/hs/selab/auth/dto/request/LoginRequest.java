package kr.ac.hs.selab.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {

    @Schema(description = "이메일")
    private final String email;
    @Schema(description = "비밀번호")
    private final String password;
}