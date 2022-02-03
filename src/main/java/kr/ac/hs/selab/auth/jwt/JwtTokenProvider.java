package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import kr.ac.hs.selab.common.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private static final int TOKEN_VALIDITY_TIME = 1000;
    private static final String CLAIMS_REGEX = ",";
    private static final String EMPTY_REGEX = "";
    private final JwtProperties jwtProperties;

    private Key key;

    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(decodeBytes());
    }

    private byte[] decodeBytes() {
        return Decoders.BASE64.decode(jwtProperties.getSecret());
    }

    public String createToken(final Authentication authentication) {
        String authorities = authoritiesToString(authentication);
        long now = currentTime();
        Date validity = expireTime(now);

        return Jwts.builder()
            .setSubject(authentication.getName())
            .setIssuer(jwtProperties.getIssuer())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public Jwt resolveToken(String authorization) {
        return new Jwt(authorization, jwtProperties);
    }

    private String authoritiesToString(Authentication authentication) {
        return authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(CLAIMS_REGEX));
    }

    private long currentTime() {
        return (new Date()).getTime();
    }

    private Date expireTime(long now) {
        final long time = jwtProperties.getTokenValidityInSeconds() * TOKEN_VALIDITY_TIME;
        return new Date(now + time);
    }

    // TODO : Provider와 상관 없는 내용임으로 분리해야 한다.
    // 이건아님
    public Authentication getAuthentication(Jwt jwt) {
        Claims claims = jwt.makeClaims();
        Collection<? extends GrantedAuthority> authorities = makeAuthorities(claims);
        User principal = newPrincipal(claims, authorities);

        return new UsernamePasswordAuthenticationToken(principal, jwt.getJwt(), authorities);
    }

    // 이건아님
    private Collection<? extends GrantedAuthority> makeAuthorities(Claims claims) {
        return Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(CLAIMS_REGEX))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    // 이건아님
    private User newPrincipal(Claims claims, Collection<? extends GrantedAuthority> authorities) {
        return new User(claims.getSubject(), EMPTY_REGEX, authorities);
    }
}