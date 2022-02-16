package kr.ac.hs.selab.auth.presentation;

import kr.ac.hs.selab.auth.converter.AuthConverter;
import kr.ac.hs.selab.auth.dto.request.AuthLoginRequest;
import kr.ac.hs.selab.auth.dto.response.AuthLoginResponse;
import kr.ac.hs.selab.auth.jwt.JwtTokenProvider;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController implements AuthSdk {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @PostMapping("/auth/login")
    public ResponseTemplate<AuthLoginResponse> login(
        @Valid @RequestBody AuthLoginRequest request) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        );

        final var authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final var token = tokenProvider.createToken(authentication);

        final var response = AuthConverter.toAuthLoginResponse(
            authentication, token);

        return ResponseTemplate.ok(ResponseMessage.AUTH_LOGIN_SUCCESS, response);
    }
}