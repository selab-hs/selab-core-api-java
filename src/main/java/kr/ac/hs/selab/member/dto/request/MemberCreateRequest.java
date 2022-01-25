package kr.ac.hs.selab.member.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateRequest {

    @NotNull
    @Email
    private final String email;
    @NotBlank
    private final String password;
    @NotBlank
    private final String studentId;
    @Pattern(regexp = "^[가-힣]{2,10}$")
    private final String name;
    @NotBlank
    @Size(max = 15)
    private final String nickname;
    private final String avatar;
}