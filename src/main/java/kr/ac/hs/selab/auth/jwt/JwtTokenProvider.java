package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

    private static final int TOKEN_VALIDITY_TIME = 1000;

    private final JwtProperties jwtProperties;
    private static final String AUTHORITIES_KEY = "auth";
    private static final String CLAIMS_REGEX = ",";
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


}