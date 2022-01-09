package kr.ac.hs.selab.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Column(name = "member_term_service")
    private boolean termService;

    @Column(name = "member_term_privacy")
    private boolean termPrivacy;

    private Terms(boolean termService, boolean termPrivacy) {
        this.termService = termService;
        this.termPrivacy = termPrivacy;
    }

    private void validate(boolean termService, boolean termPrivacy) {
        if (unChecked(termService, termPrivacy)) {
            throw new InvalidArgumentException(ErrorMessage.MEMBER_TERMS_INVALID_ARGUMENT_ERROR);
        }
    }

    private boolean unChecked(boolean termService, boolean termPrivacy) {
        return !termService || !termPrivacy;
    }

    public static Terms of(boolean termService, boolean termPrivacy) {
        return new Terms(termService, termPrivacy);
    }
}