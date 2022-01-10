package kr.ac.hs.selab.member.domain.vo;

import java.util.Arrays;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN(new SimpleGrantedAuthority("ROLE_ADMIN"), "관리자"),
    MIDDLE_ADMIN(new SimpleGrantedAuthority("ROLE_MIDDLE_ADMIN"), "중간 관리자"),
    USER(new SimpleGrantedAuthority("ROLE_USER"), "일반 사용자"),
    GUEST(new SimpleGrantedAuthority("ROLE_GUEST"), "게스트");

    private final GrantedAuthority grantedAuthority;
    private final String description;

    public static Role of(String description) {
        return Arrays.stream(Role.values())
            .filter(r -> r.isEquals(description))
            .findAny()
            .orElse(GUEST);
    }

    private boolean isEquals(String description) {
        return this.getDescription().equals(description);
    }
}