package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

public class Jwt {

    private static final int TOKEN_VALIDITY_TIME = 1000;
    private static final String AUTHORITIES_KEY = "auth";

    private final String issuer;
    private final Key key;
    private final Long tokenValidityInSeconds;

    public Jwt(String issuer, Key key, Long tokenValidityInSeconds) {
        this.issuer = issuer;
        this.key = key;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String createToken(final Authentication authentication) {
        var authorities = authoritiesToString(authentication);
        var now = currentTime();
        var validity = expireTime(now);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuer(issuer)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    private String authoritiesToString(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    private long currentTime() {
        return (new Date()).getTime();
    }

    private Date expireTime(long now) {
        return new Date(now + tokenValidityInSeconds * TOKEN_VALIDITY_TIME);
    }
}