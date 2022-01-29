package kr.ac.hs.selab.auth.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AuthLoginRequest {
    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;
}