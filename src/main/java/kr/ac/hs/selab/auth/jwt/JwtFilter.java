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
        "/api/v1/members",
        "/api/v1/auth/login"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        final String servletPath = httpServletRequest.getServletPath();

        if (!EXCLUDE_URL.contains(servletPath)) {
            String jwt = tokenProvider.resolveToken(httpServletRequest);
            if (tokenProvider.validateToken(jwt)) {
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}