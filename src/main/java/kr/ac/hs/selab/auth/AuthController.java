package kr.ac.hs.selab.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Auth")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;
    private final AuthConverter authConverter;

    @ApiOperation("로그인")
    @PostMapping("/members/login")
    public ResponseEntity<ResponseTemplate<LoginResponse>> login(@RequestBody LoginRequest dto) {
        final LoginResponse loginResponse = loginResponse(dto);
        return ResponseTemplate.of(ResponseMessage.HEALTH_GOOD, loginResponse);
    }

    private LoginResponse loginResponse(LoginRequest dto) {
        TokenBundle token = newToken(dto);
        Member member = memberService.findByEmail(Email.of(dto.getEmail()));

        return authConverter.toLoginResponse(member, token);
    }

    private TokenBundle newToken(LoginRequest loginDto) {
        return new TokenBundle(newJwtToken(loginDto));
    }

    private String newJwtToken(LoginRequest loginDto) {
        return tokenProvider.createToken(newAuthentication(loginDto));
    }

    private Authentication newAuthentication(LoginRequest loginDto) {
        return newAuthentication(newAuthenticationToken(loginDto));
    }

    private UsernamePasswordAuthenticationToken newAuthenticationToken(LoginRequest loginDto) {
        return new UsernamePasswordAuthenticationToken(
            email(loginDto),
            password(loginDto)
        );
    }

    private String email(LoginRequest loginDto) {
        return loginDto.getEmail();
    }

    private String password(LoginRequest loginDto) {
        return loginDto.getPassword();
    }

    private Authentication newAuthentication(
        UsernamePasswordAuthenticationToken authenticationToken) {
        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}