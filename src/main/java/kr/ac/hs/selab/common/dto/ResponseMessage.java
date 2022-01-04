package kr.ac.hs.selab.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {
    /**
     * Health CHECK
     */
    HEALTH_GOOD("R-H-001", HttpStatus.OK, "서버 상태가 양호합니다."),

    /**
     * MEMBER SUCCESS RESPONSE MESSAGE
     **/
    MEMBER_INSERT_SUCCESS("R-M-001", HttpStatus.CREATED, "회원 가입을 완료했습니다."),

    ;

    private final String code;
    private final HttpStatus status;
    private final String message;
}