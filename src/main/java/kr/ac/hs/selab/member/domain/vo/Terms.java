package kr.ac.hs.selab.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Column(name = "member_term_service")
    private String termService;

    @Column(name = "member_term_privacy")
    private String termPrivacy;
}