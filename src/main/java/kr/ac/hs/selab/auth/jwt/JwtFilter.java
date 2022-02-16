package kr.ac.hs.selab.auth.jwt;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class JwtFilter implements Filter {

    @NotNull
    private final JwtTokenProvider tokenProvider;

    private final static List<String> EXCLUDE_URL = List.of(
            "/api/v1/members/sign",
            "/api/v1/auth/login"
    );

    // TODO 중복된 코드임
    private final static List<String> SWAGGER_EXCLUDE_URL = List.of(
            "/swagger",
            "/swagger-ui/springfox.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/springfox.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-ui.css",
            "/swagger-resources/configuration/ui",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-resources/configuration/security",
            "/swagger-resources",
            "/v2/api-docs",
            "/swagger-ui/index.html"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        final String servletPath = httpServletRequest.getServletPath();

        // 필터가 제기능을 수행하지 못함
        if (!EXCLUDE_URL.contains(servletPath) && !SWAGGER_EXCLUDE_URL.contains(servletPath)) {
            System.out.println(servletPath);
            String jwt = tokenProvider.resolveToken(httpServletRequest);
            if (tokenProvider.validateToken(jwt)) {
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}