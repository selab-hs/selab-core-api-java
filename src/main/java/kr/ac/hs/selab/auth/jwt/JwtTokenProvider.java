package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kr.ac.hs.selab.common.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

    private final JwtProperties jwtProperties;
    private Key key;

    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(decodeBytes());
    }

    private byte[] decodeBytes() {
        return Decoders.BASE64.decode(jwtProperties.getSecret());
    }

    public Jwt create() {
        return new Jwt(
                jwtProperties.getIssuer(),
                key,
                jwtProperties.getTokenValidityInSeconds()
        );
    }

    // TODO : Provider와 상관 없는 내용임으로 분리해야 한다.
    // 이건아님
    public JwtAuthentication getAuthentication(String token) {
        var claims = makeClaims(token);
        var authorities = makeAuthorities(claims);

        return new JwtAuthentication(
                new User(claims.getSubject(), "", authorities),
                token,
                authorities
        );
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
        return Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}