package kr.ac.hs.selab.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    /**
     * INTERNAL ERROR RESPONSE MESSAGE
     **/
    INTERNAL_SERVER_ERROR("E-IS001", HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 에러가 발생했습니다."),

    ;

    private final String code;
    private final HttpStatus status;
    private final String message;
}