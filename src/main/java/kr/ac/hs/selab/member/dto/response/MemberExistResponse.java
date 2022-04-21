package kr.ac.hs.selab.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value(staticConstructor = "of")
public class MemberExistResponse {
    @Schema(description = "회원 가입 여부")
    boolean isSignup;
}
