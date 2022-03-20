package kr.ac.hs.selab.auth.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class JwtAuthentication extends UsernamePasswordAuthenticationToken {

    public JwtAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public Authentication getAuthentication() {
        return this;
    }

    public void setAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(this);
    }
}