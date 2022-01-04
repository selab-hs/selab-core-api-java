package kr.ac.hs.selab.error.dto;

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

    /**
     * MEMBER ERROR RESPONSE MESSAGE
     **/
    MEMBER_EMAIL_INVALID_ARGUMENT_ERROR("E-M001", HttpStatus.BAD_REQUEST,
        "회원 이메일 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NAME_INVALID_ARGUMENT_ERROR("E-M002", HttpStatus.BAD_REQUEST,
        "회원 이름 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NICKNAME_INVALID_ARGUMENT_ERROR("E-M003", HttpStatus.BAD_REQUEST,
        "회원 닉네임 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_STUDENT_ID_INVALID_ARGUMENT_ERROR("E-M004", HttpStatus.BAD_REQUEST,
        "회원 학번 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_PASSWORD_INVALID_ARGUMENT_ERROR("E-M005", HttpStatus.BAD_REQUEST,
        "회원 비밀번호 입력 규칙에서 문제가 발생했습니다."),

    ;

    private final String code;
    private final HttpStatus status;
    private final String message;
}