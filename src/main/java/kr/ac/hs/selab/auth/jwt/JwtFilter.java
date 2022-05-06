package kr.ac.hs.selab.auth.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Slf4j
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
            "/swagger-ui/index.html",
            "/favicon.ico"
    );

    private final static String HEALTH = "/health";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TOKEN = "Bearer ";
    private static final int BEARER_TOKEN_SUBSTRING_INDEX = 7;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        var httpServletRequest = (HttpServletRequest) servletRequest;

        final var path = httpServletRequest.getServletPath();

        log.info("[INFO] servlet request path {}", path);

        if (isChecking(path)) {
            var bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TOKEN)) {
                var jwt = bearerToken.substring(BEARER_TOKEN_SUBSTRING_INDEX);
                if (tokenProvider.validateToken(jwt)) {
                    var authentication = tokenProvider.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isChecking(String path) {
        return !EXCLUDE_URL.contains(path) && !SWAGGER_EXCLUDE_URL.contains(path) && !HEALTH.equals(path);
    }
}