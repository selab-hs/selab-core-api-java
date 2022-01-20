package kr.ac.hs.selab.auth.jwt;

import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

// TODO : JWT를 더 예쁘게 관리할 수 있도록 수정 필요
public class JwtToken {

    public JwtToken() {

    }

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TOKEN = "Bearer ";
    private static final int BEARER_TOKEN_SUBSTRING_INDEX = 7;

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TOKEN)) {
            return bearerToken.substring(BEARER_TOKEN_SUBSTRING_INDEX);
        }
        return null;
    }

}
