package kr.ac.hs.selab.error.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    /**
     * CONFLICT ERROR RESPONSE MESSAGE
     **/
    CONFLICT_ERROR("E-C-0001", HttpStatus.CONFLICT, "예기치 못한 에러가 발생했습니다."),

    /**
     * MEMBER ERROR RESPONSE MESSAGE
     **/
    MEMBER_EMAIL_INVALID_ARGUMENT_ERROR("E-M-0001", HttpStatus.BAD_REQUEST,
        "회원 이메일 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NAME_INVALID_ARGUMENT_ERROR("E-M-0002", HttpStatus.BAD_REQUEST,
        "회원 이름 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NICKNAME_INVALID_ARGUMENT_ERROR("E-M-0003", HttpStatus.BAD_REQUEST,
        "회원 닉네임 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_STUDENT_ID_INVALID_ARGUMENT_ERROR("E-M-0004", HttpStatus.BAD_REQUEST,
        "회원 학번 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_PASSWORD_INVALID_ARGUMENT_ERROR("E-M-0005", HttpStatus.BAD_REQUEST,
        "회원 비밀번호 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_TERMS_INVALID_ARGUMENT_ERROR("E-M-0006", HttpStatus.BAD_REQUEST,
        "회원 정책 규칙에서 문제가 발생했습니다."),
    MEMBER_EMAIL_DUPLICATION_ERROR("E-M-0007", HttpStatus.BAD_REQUEST,
        "중복된 회원 이메일 입력입니다."),
    MEMBER_STUDENT_ID_DUPLICATION_ERROR("E-M-0008", HttpStatus.BAD_REQUEST,
        "중복된 회원 학번 입력입니다."),
    MEMBER_NICKNAME_DUPLICATION_ERROR("E-M-0009", HttpStatus.BAD_REQUEST,
        "중복된 회원 닉네임 입력입니다."),
    MEMBER_NOT_EXISTS_ERROR("E-M-0010", HttpStatus.NOT_FOUND, "존재하지 않는 회원 입니다."),

    /**
     * BOARD ERROR RESPONSE MESSAGE
     **/
    BOARD_NOT_EXISTS_ERROR("E-B-0001", HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다."),
    BOARD_TITLE_DUPLICATION_ERROR("E-B-0002", HttpStatus.BAD_REQUEST, "중복된 게시판 제목입니다."),

    ;

    private final String code;
    private final HttpStatus status;
    private final String detail;
}