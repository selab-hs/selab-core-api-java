package kr.ac.hs.selab.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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

    public static Terms of(boolean termService, boolean termPrivacy) {
        return new Terms(termService, termPrivacy);
    }
}