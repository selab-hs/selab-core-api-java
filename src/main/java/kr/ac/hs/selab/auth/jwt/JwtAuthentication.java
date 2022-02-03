package kr.ac.hs.selab.auth.jwt;

import io.jsonwebtoken.Claims;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@NoArgsConstructor
public class JwtAuthentication {

    public Authentication getAuthentication(Jwt jwt) {
        Claims claims = jwt.makeClaims();
        Collection<? extends GrantedAuthority> authorities = makeAuthorities(claims);
        User principal = newPrincipal(claims, authorities);

        return new UsernamePasswordAuthenticationToken(principal, jwt.getJwt(), authorities);
    }

    private static final String AUTHORITIES_KEY = "auth";
    private static final String CLAIMS_REGEX = ",";

    private static final String EMPTY_REGEX = "";

    private Collection<? extends GrantedAuthority> makeAuthorities(Claims claims) {
        final String[] keys = claims.get(AUTHORITIES_KEY)
            .toString().split(CLAIMS_REGEX);

        return Arrays.stream(keys)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    private User newPrincipal(Claims claims, Collection<? extends GrantedAuthority> authorities) {
        return new User(claims.getSubject(), EMPTY_REGEX, authorities);
    }
}
