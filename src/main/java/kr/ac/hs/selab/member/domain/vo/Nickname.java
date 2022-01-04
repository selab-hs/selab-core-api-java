package kr.ac.hs.selab.member.domain.vo;

import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import kr.ac.hs.selab.common.utils.ValidationUtils;
import kr.ac.hs.selab.error.dto.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nickname {

    @Transient
    private static final int MAX_LENGTH = 15;

    @Column(name = "member_nickname", unique = true)
    private String nickname;

    public Nickname(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    public void validate(String nickname) {
        if (ValidationUtils.isWrong(nickname, MAX_LENGTH)) {
            throw new InvalidArgumentException(ErrorMessage.MEMBER_NICKNAME_INVALID_ARGUMENT_ERROR);
        }
    }
}