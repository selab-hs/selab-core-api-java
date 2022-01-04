package kr.ac.hs.selab.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Avatar {

    @Column(name = "member_avatar")
    private String avatar;
}