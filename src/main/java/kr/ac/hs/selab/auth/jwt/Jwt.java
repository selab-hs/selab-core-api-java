package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class Jwt {

    private final String issuer;
    private final Key key;
    private final Long tokenValidityInSeconds;

    public Jwt(String issuer, Key key, Long tokenValidityInSeconds) {
        this.issuer = issuer;
        this.key = key;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String create(Collection<? extends GrantedAuthority> authorities, String name) {
        var now = currentTime();
        var validity = expireTime(now);

        return Jwts.builder()
                .setSubject(name)
                .setIssuer(issuer)
                .claim("auth", authoritiesToString(authorities))
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    private String authoritiesToString(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    private long currentTime() {
        return (new Date()).getTime();
    }

    private Date expireTime(long now) {
        return new Date(now + tokenValidityInSeconds * 1000);
    }

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
            log.info("JWT 토큰이 잘못되었습니다.");
            e.printStackTrace();
        }
        return false;
    }
}