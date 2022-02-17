package kr.ac.hs.selab.error.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    /**
     * CONFLICT ERROR RESPONSE MESSAGE
     **/
    CONFLICT_ERROR("E-C-0001", "예기치 못한 에러가 발생했습니다."),

    /**
     * MEMBER ERROR RESPONSE MESSAGE
     **/
    MEMBER_EMAIL_INVALID_ARGUMENT_ERROR("E-M-0001",
            "회원 이메일 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NAME_INVALID_ARGUMENT_ERROR("E-M-0002",
            "회원 이름 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_NICKNAME_INVALID_ARGUMENT_ERROR("E-M-0003",
            "회원 닉네임 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_STUDENT_ID_INVALID_ARGUMENT_ERROR("E-M-0004",
            "회원 학번 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_PASSWORD_INVALID_ARGUMENT_ERROR("E-M-0005",
            "회원 비밀번호 입력 규칙에서 문제가 발생했습니다."),
    MEMBER_TERMS_INVALID_ARGUMENT_ERROR("E-M-0006",
            "회원 정책 규칙에서 문제가 발생했습니다."),
    MEMBER_EMAIL_DUPLICATION_ERROR("E-M-0007",
            "중복된 회원 이메일 입력입니다."),
    MEMBER_STUDENT_ID_DUPLICATION_ERROR("E-M-0008",
            "중복된 회원 학번 입력입니다."),
    MEMBER_NICKNAME_DUPLICATION_ERROR("E-M-0009",
            "중복된 회원 닉네임 입력입니다."),
    MEMBER_NOT_EXISTS_ERROR("E-M-0010", "존재하지 않는 회원 입니다."),

    /**
     * BOARD ERROR RESPONSE MESSAGE
     **/
    BOARD_NOT_EXISTS_ERROR("E-B-0001", "존재하지 않는 게시판입니다."),

    /**
     * POST ERROR RESPONSE MESSAGE
     */
    POST_NOT_EXISTS_ERROR("E-P-0001", "존재하지 않는 게시글입니다."),

    /**
     * COMMENT ERROR RESPONSE MESSAGE
     */
    COMMENT_NOT_EXISTS_ERROR("E-C-0001", "존재하지 않는 댓글입니다."),

    /**
     * TERMS ERROR RESPONSE MESSAGE
     **/
    TERMS_NOT_EXISTS_ERROR("E-T-0001", "존재하지 않는 약관 카테코리입니다."),

    /**
     * COMMENT LIKE ERROR RESPONSE MESSAGE
     */
    COMMENT_LIKE_EXISTS_ERROR("E-C-0001", "해당 회원은 댓글에 이미 좋아요를 했습니다."),
    COMMENT_LIKE_NON_EXISTS_ERROR("E-C-0002", "해당 회원은 댓글에 좋아요를 한 적이 없습니다."),

    ;

    private final String code;
    private final String detail;
}