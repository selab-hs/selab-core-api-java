package kr.ac.hs.selab.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemberResponse {

    private final String nickname;
}