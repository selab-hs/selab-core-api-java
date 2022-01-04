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
public class Email {

    @Transient
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Column(name = "member_email", unique = true)
    private String email;

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    private void validate(String email) {
        if (ValidationUtils.isWrong(email, EMAIL_REGEX)) {
            throw new InvalidArgumentException(ErrorMessage.MEMBER_EMAIL_INVALID_ARGUMENT_ERROR);
        }
    }
}