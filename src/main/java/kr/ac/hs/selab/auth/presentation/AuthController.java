package kr.ac.hs.selab.auth.presentation;

import kr.ac.hs.selab.auth.converter.AuthConverter;
import kr.ac.hs.selab.auth.dto.request.AuthLoginRequest;
import kr.ac.hs.selab.auth.dto.response.AuthLoginResponse;
import kr.ac.hs.selab.auth.jwt.JwtAuthentication;
import kr.ac.hs.selab.auth.jwt.JwtTokenProvider;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController implements AuthSdk {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // TODO Redis를 통해 token data 관리 진행
    @Override
    @PostMapping("/auth/login")
    public ResponseTemplate<AuthLoginResponse> login(
            @Validated @RequestBody AuthLoginRequest request) {
        JwtAuthentication jwtAuthentication = new JwtAuthentication(
                request.getEmail(),
                request.getPassword()
        );

        final var authentication = authenticationManagerBuilder.getObject()
                .authenticate(jwtAuthentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final var token = jwtTokenProvider.create()
                .create(
                        authentication.getAuthorities(),
                        authentication.getName()
                );

        final var response = AuthConverter.toAuthLoginResponse(
                authentication.getName(),
                token
        );

        log.info("[로그인] {}", jwtAuthentication.getName());

        return ResponseTemplate.ok(ResponseMessage.AUTH_LOGIN_SUCCESS, response);
    }

    @PostMapping("/test")
    public String test() {
        return "test";
    }
}