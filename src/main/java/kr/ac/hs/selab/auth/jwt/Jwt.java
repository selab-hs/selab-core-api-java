package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import kr.ac.hs.selab.common.properties.JwtProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jwt {

    public static final String BEARER_TOKEN = "Bearer ";
    private static final int BEARER_TOKEN_SUBSTRING_INDEX = 7;
    private final JwtProperties jwtProperties;
    @Getter
    private final String jwt;
    private final Key key;

    public Jwt(String jwt, JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
        validate(jwt);
        this.jwt = resolveToken(jwt);
    }

    public void validate(String jwt) {
        if (!jwt.startsWith(BEARER_TOKEN)) {
            throw new IllegalArgumentException("잘못된 JWT이다.");
        }
        final String token = jwt.substring(BEARER_TOKEN_SUBSTRING_INDEX);

        try {
            Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
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
    }

    public String resolveToken(String bearerToken) {
        return bearerToken.substring(BEARER_TOKEN_SUBSTRING_INDEX);
    }

    public Claims makeClaims() {
        return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)
            .getBody();
    }
}