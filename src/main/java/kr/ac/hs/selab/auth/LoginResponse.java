package kr.ac.hs.selab.auth;

import kr.ac.hs.oing.member.domain.vo.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private final Long id;
    private final String nickname;
    private final Role role;
    private final String clubName;
    private final String token;
}