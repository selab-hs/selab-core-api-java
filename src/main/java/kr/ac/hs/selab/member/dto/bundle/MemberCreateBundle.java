package kr.ac.hs.selab.member.dto.bundle;

import kr.ac.hs.selab.member.domain.vo.Avatar;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.domain.vo.Terms;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberCreateBundle {

    private final String email;
    private final Password password;
    private final String studentId;
    private final String name;
    private final String nickname;
    private final Avatar avatar;
    private final Terms terms;
}