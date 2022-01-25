package kr.ac.hs.selab.terms.domain.vo;

import java.util.Arrays;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    SERVICE("서비스 정책"),
    PRIVACY("개인정보 정책");

    private final String description;

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