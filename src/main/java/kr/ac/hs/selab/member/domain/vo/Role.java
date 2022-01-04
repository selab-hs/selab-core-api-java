package kr.ac.hs.selab.member.domain.vo;

import java.util.Arrays;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    GUEST("GUEST", "게스트");

    private final String type;
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