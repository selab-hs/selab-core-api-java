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
import kr.ac.hs.selab.common.properties.JwtProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";
    private static final int TOKEN_VALIDITY_TIME = 1000;
    private static final String CLAIMS_REGEX = ",";
    private static final String EMPTY_REGEX = "";
    private final String issuer;
    private final String secret;
    private final Long tokenValidityInMilliseconds;

    private Key key;


    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.issuer = jwtProperties.getIssuer();
        this.secret = jwtProperties.getSecret();
        this.tokenValidityInMilliseconds =
            jwtProperties.getTokenValidityInSeconds() * TOKEN_VALIDITY_TIME;
    }

    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(decodeBytes());
    }

    private byte[] decodeBytes() {
        return Decoders.BASE64.decode(secret);
    }

    public String createToken(Authentication authentication) {
        String authorities = authoritiesToString(authentication);
        long now = currentTime();
        Date validity = expireTime(now);

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
            .collect(Collectors.joining(CLAIMS_REGEX));
    }

    private long currentTime() {
        return (new Date()).getTime();
    }

    private Date expireTime(long now) {
        return new Date(now + this.tokenValidityInMilliseconds);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = makeClaims(token);
        Collection<? extends GrantedAuthority> authorities = makeAuthorities(claims);
        User principal = newPrincipal(claims, authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private Claims makeClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Collection<? extends GrantedAuthority> makeAuthorities(Claims claims) {
        return Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(CLAIMS_REGEX))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    private User newPrincipal(Claims claims, Collection<? extends GrantedAuthority> authorities) {
        return new User(claims.getSubject(), EMPTY_REGEX, authorities);
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
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}