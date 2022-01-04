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
    NOT_DEFINE_ERROR("E-N-001", HttpStatus.NOT_FOUND, "예기치 못한 에러가 발생했습니다."),

    /**
     * MEMBER ERROR RESPONSE MESSAGE
     **/
    MEMBER_EMAIL_INVALID_ARGUMENT_ERROR("E-M-001", HttpStatus.BAD_REQUEST,
        "회원 이메일 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NAME_INVALID_ARGUMENT_ERROR("E-M-002", HttpStatus.BAD_REQUEST,
        "회원 이름 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NICKNAME_INVALID_ARGUMENT_ERROR("E-M-003", HttpStatus.BAD_REQUEST,
        "회원 닉네임 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_STUDENT_ID_INVALID_ARGUMENT_ERROR("E-M-004", HttpStatus.BAD_REQUEST,
        "회원 학번 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_PASSWORD_INVALID_ARGUMENT_ERROR("E-M-005", HttpStatus.BAD_REQUEST,
        "회원 비밀번호 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_TERMS_INVALID_ARGUMENT_ERROR("E-M-006", HttpStatus.BAD_REQUEST,
        "회원 정책 규칙에서 문제가 발생했습니다."),
    MEMBER_EMAIL_DUPLICATION_ERROR("E-M-007", HttpStatus.BAD_REQUEST,
        "중복된 회원 이메일 입력입니다."),
    MEMBER_STUDENT_ID_DUPLICATION_ERROR("E-M-008", HttpStatus.BAD_REQUEST,
        "중복된 회원 학번 입력입니다."),
    MEMBER_NICKNAME_DUPLICATION_ERROR("E-M-009", HttpStatus.BAD_REQUEST,
        "중복된 회원 닉네임 입력입니다."),

    ;

    private final String code;
    private final HttpStatus status;
    private final String detail;
}