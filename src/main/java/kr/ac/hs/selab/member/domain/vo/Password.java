package kr.ac.hs.selab.member.domain.vo;

import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    /**
     * 최소 8자 ~ 최대 30자 최소 영문자 1자 최소 숫자 1자 최소 특수문자 1자 :: $@$!%*#?& ^(?=.*[~!@#$%^&*()_+`\-=\[\]\{\};':\",.\<>?])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\S{8,30}$
     */
    @Transient
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[가-힣A-Za-z\\d$@$!%*#?&]{8,30}$";

    @Column(name = "member_password")
    private String password;

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    // TODO ErrorMessage가 전부 Detail 해보이는데, 각 하나씩 구현하기보다는, 올바르지 않은 사용자 값 : {}
    //이런식의 input값 에러메세지가 통일되는게 좋아보입니다.
    private void validate(String password) {
        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            throw new InvalidArgumentException(
                ErrorMessage.MEMBER_PASSWORD_INVALID_ARGUMENT_ERROR);
        }
    }

    // TODO Spring Converter를 통해서 받는 것이 좋습니다!
    public Password encode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return this;
    }
}