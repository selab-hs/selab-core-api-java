package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

// TODO : 조금씩 뿌시자!
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
        var authorities = authoritiesToString(authentication);
        var now = currentTime();
        var validity = expireTime(now);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuer(jwtProperties.getIssuer())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
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
        final var time = jwtProperties.getTokenValidityInSeconds() * TOKEN_VALIDITY_TIME;
        return new Date(now + time);
    }

    // TODO : Provider와 상관 없는 내용임으로 분리해야 한다.
    // 이건아님
    public Authentication getAuthentication(String token) {
        var claims = makeClaims(token);
        var authorities = makeAuthorities(claims);
        var principal = newPrincipal(claims, authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    // 이건아님
    private Claims makeClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
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

    // 이건아님
    // Exception 잡아서 진행하기!!
    public boolean validateToken(String token) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}