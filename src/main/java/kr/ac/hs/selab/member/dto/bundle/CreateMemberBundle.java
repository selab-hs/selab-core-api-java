package kr.ac.hs.selab.member.dto.bundle;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ac.hs.selab.member.domain.vo.Avatar;
import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Name;
import kr.ac.hs.selab.member.domain.vo.Nickname;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.domain.vo.StudentId;
import kr.ac.hs.selab.member.domain.vo.Terms;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateMemberBundle {

    private final Email email;
    private final Password password;
    private final StudentId studentId;
    private final Name name;
    private final Nickname nickname;
    private final Avatar avatar;
    private final Terms terms;
}