package kr.ac.hs.selab.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Avatar {

    // TODO : DEFAULT_IMAGE_URL 교체 작업 필요, PROPERTIES로 관리하자!
    @Transient
    private String DEFAULT_IMAGE_URL = "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/2TKUKXYMQF7ASZEUJLG7L4GM4I.jpg";

    @Column(name = "member_avatar")
    private String avatar;

    public Avatar(String avatar) {
        this.avatar = validate(avatar);
    }

    private String validate(String avatar) {
        if (StringUtils.hasText(avatar)) {
            return avatar;
        }
        return DEFAULT_IMAGE_URL;
    }
}