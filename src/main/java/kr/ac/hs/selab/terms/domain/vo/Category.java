package kr.ac.hs.selab.terms.domain.vo;

import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Category {
    SERVICE("서비스 정책"),
    PRIVACY("개인정보 정책");

    private final String description;

    //TODO : EnumMap을 이용하여, cache를 하나 만들기
    public static Category of(String description) {
        return Arrays.stream(Category.values())
            .filter(r -> r.isEquals(description))
            .findAny()
            .orElseThrow(() -> new NonExitsException(ErrorMessage.TERMS_NOT_EXISTS_ERROR));
    }

    private boolean isEquals(String description) {
        return this.getDescription().equals(description);
    }
}