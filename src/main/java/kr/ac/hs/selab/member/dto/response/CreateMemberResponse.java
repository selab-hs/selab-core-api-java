package kr.ac.hs.selab.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateMemberResponse {

    private final String email;
    private final String nickname;
}