package kr.ac.hs.selab.auth.presentation;

import kr.ac.hs.selab.auth.converter.AuthConverter;
import kr.ac.hs.selab.auth.dto.request.LoginRequest;
import kr.ac.hs.selab.auth.dto.response.LoginResponse;
import kr.ac.hs.selab.auth.jwt.JwtTokenProvider;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Email;
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

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController extends AuthSwaggerController {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;
    private final AuthConverter authConverter;

    @Override
    @PostMapping("/auth/login")
    public ResponseEntity<ResponseTemplate<LoginResponse>> login(@RequestBody LoginRequest dto) {
        final LoginResponse loginResponse = loginResponse(dto);
        return ResponseTemplate.of(ResponseMessage.HEALTH_GOOD, loginResponse);
    }

    private LoginResponse loginResponse(LoginRequest request) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        Member member = memberService.findByEmail(Email.of(request.getEmail()));

        return authConverter.toLoginResponse(member, token);
    }
}