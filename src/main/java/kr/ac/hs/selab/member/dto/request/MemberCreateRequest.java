package kr.ac.hs.selab.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberCreateRequest {
    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String studentId;

    @Pattern(regexp = "^[가-힣]{2,10}$")
    private String name;

    @NotBlank
    @Size(max = 15)
    private String nickname;

    private String avatar;
}