package kr.ac.hs.selab.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Schema(description = "회원가입 요청")
@Getter
@RequiredArgsConstructor
public class CreateMemberRequest {

    @Schema(description = "이메일")
    private final String email;
    @Schema(description = "비밀번호")
    private final String password;
    @Schema(description = "학번")
    private final String studentId;
    @Schema(description = "이름")
    private final String name;
    @Schema(description = "닉네임")
    private final String nickname;
    @Schema(description = "회원이미지")
    private final String avatar;
    @Schema(description = "서비스 이용 동의")
    private final boolean termService;
    @Schema(description = "개인정보 동의")
    private final boolean termPrivacy;
}