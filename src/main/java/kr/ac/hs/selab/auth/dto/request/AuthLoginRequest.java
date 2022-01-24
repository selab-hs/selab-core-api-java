package kr.ac.hs.selab.auth.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthLoginRequest {

    @NotNull
    @Email
    private final String email;
    @NotBlank
    private final String password;
}