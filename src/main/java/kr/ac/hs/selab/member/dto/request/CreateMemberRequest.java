package kr.ac.hs.selab.member.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemberRequest {

    private final String email;
    private final String password;
    private final String studentId;
    private final String name;
    private final String nickname;
    private final String avatar;
    private final boolean termService;
    private final boolean termPrivacy;
}