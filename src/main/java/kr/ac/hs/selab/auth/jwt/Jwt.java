package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class Jwt {

    private final String issuer;
    private final Key key;
    private final Long tokenValidityInSeconds;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String name;

    public Jwt(String issuer, Key key, Long tokenValidityInSeconds, Collection<? extends GrantedAuthority> authorities, String name) {
        this.issuer = issuer;
        this.key = key;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
        this.authorities = authorities;
        this.name = name;
    }

    public String create() {
        var now = currentTime();
        var validity = expireTime(now);

        return Jwts.builder()
                .setSubject(name)
                .setIssuer(issuer)
                .claim("auth", authoritiesToString())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    private String authoritiesToString() {
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
}