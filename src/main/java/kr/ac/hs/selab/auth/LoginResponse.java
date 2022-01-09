package kr.ac.hs.selab.auth;

import kr.ac.hs.selab.member.domain.vo.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private final String nickname;
    private final String role;
    private final String token;
}