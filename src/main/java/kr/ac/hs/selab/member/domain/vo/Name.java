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
public class Name {

    @Transient
    private static final String NAME_REGEX = "^[가-힣]{2,10}$";

    @Column(name = "member_name")
    private String name;

    private Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (ValidationUtils.isWrongWithRegex(name, NAME_REGEX)) {
            throw new InvalidArgumentException(ErrorMessage.MEMBER_NAME_INVALID_ARGUMENT_ERROR);
        }
    }

    public static Name of(String name) {
        return new Name(name);
    }
}