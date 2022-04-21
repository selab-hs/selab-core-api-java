package kr.ac.hs.selab.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@NoArgsConstructor
@Getter
public class MemberExistRequest {
    @Email(message = "이메일 양식이 아닙니다.")
    private String memberEmail;
}
