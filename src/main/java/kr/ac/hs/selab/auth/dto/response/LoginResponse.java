package kr.ac.hs.selab.auth.dto.response;

import kr.ac.hs.selab.member.domain.vo.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class LoginResponse {

    private final String nickname;
    private final String role;
    private final String token;
}