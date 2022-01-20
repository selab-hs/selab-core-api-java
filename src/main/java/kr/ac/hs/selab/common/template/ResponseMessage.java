package kr.ac.hs.selab.common.template;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage implements Serializable {
    /**
     * Health CHECK
     */
    HEALTH_GOOD("R-H-0001", "서버 상태가 양호합니다."),

    /**
     * MEMBER SUCCESS RESPONSE MESSAGE
     **/
    MEMBER_INSERT_SUCCESS("R-M-0001", "회원 가입을 완료했습니다."),

    /**
     * AUTH SUCCESS RESPONSE MESSAGE
     **/
    AUTH_LOGIN_SUCCESS("R-A-0001", "로그인을 성공했습니다."),

    ;

    private final String code;
    private final String detail;
}