package kr.ac.hs.selab.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import kr.ac.hs.selab.common.utils.ValidationUtils;
import kr.ac.hs.selab.error.dto.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    private Password(String password) {
        validate(password);
        this.password = password;
    }

    private void validate(String password) {
        if (ValidationUtils.isWrong(password, PASSWORD_REGEX)) {
            throw new InvalidArgumentException(ErrorMessage.MEMBER_PASSWORD_INVALID_ARGUMENT_ERROR);
        }
    }

    public static Password of(String password) {
        return new Password(password);
    }
}