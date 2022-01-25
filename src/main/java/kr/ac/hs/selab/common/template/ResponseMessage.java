package kr.ac.hs.selab.common.template;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

    /**
     * BOARD SUCCESS RESPONSE MESSAGE
     **/
    BOARD_CREATE_SUCCESS("R-B-0001", "게시판 생성을 완료했습니다."),
    BOARD_FIND_SUCCESS("R-B-0002", "게시판을 정상적으로 찾았습니다."),
    BOARD_UPDATE_SUCCESS("R-B-0003", "게시판을 정상적으로 수정했습니다."),
    BOARD_DELETE_SUCCESS("R-B-0004", "게시판을 정상적으로 삭제 했습니다."),

    ;

    private final String code;
    private final String detail;
}