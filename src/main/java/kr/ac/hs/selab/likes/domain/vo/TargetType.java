package kr.ac.hs.selab.likes.domain.vo;

import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum TargetType {
    POST("post"),
    COMMENT("comment");

    private final String value;

    public static TargetType of(String targetType) {
        return Arrays.stream(values())
                .filter(type -> type.getValue().equals(targetType))
                .findFirst()
                .orElseThrow(() -> new NonExitsException(ErrorMessage.LIKES_TARGET_NOT_EXISTS_ERROR));
    }
}
