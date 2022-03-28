package kr.ac.hs.selab.common.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage implements Serializable {
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

    /**
     * POST SUCCESS RESPONSE MESSAGE
     */
    POST_CREATE_SUCCESS("R-P-0001", "게시글 생성을 완료했습니다."),
    POST_FIND_SUCCESS("R-P-0002", "게시글을 정상적으로 찾았습니다."),
    POST_UPDATE_SUCCESS("R-P-0003", "게시글을 정상적으로 수정했습니다."),
    POST_DELETE_SUCCESS("R-P-0004", "게시글을 정상적으로 삭제 했습니다."),

    /**
     * COMMENT SUCCESS RESPONSE MESSAGE
     */
    COMMENT_CREATE_SUCCESS("R-C-0001", "댓글 생성을 완료했습니다."),
    COMMENT_FIND_SUCCESS("R-C-0002", "댓글을 정상적으로 찾았습니다."),
    COMMENT_UPDATE_SUCCESS("R-C-0003", "댓글을 정상적으로 수정했습니다."),
    COMMENT_DELETE_SUCCESS("R-C-0004", "댓글을 정상적으로 삭제했습니다."),

    /**
     * POST LIKE SUCCESS RESPONSE MESSAGE
     */
    POST_LIKE_CREATE_SUCCESS("R-P-0001", "게시글 좋아요 성공했습니다."),
    POST_LIKE_FIND_SUCCESS("R-P-0002", "게시글 좋아요 찾았습니다."),
    POST_LIKE_DELETE_SUCCESS("R-P-0003", "게시글 좋아요 해제했습니다."),

    /**
     * COMMENT LIKE SUCCESS RESPONSE MESSAGE
     */
    COMMENT_LIKE_CREATE_SUCCESS("R-L-0001", "댓글 좋아요 성공했습니다."),
    COMMENT_LIKE_FIND_SUCCESS("R-L-0002", "댓글 좋아요 찾았습니다."),
    COMMENT_LIKE_DELETE_SUCCESS("R-L-0003", "댓글 좋아요 해제했습니다."),

    /**
     * NOTICE SUCCESS RESPONSE MASSAGE
     */
    NOTICE_CREATE_SUCCESS("R-N-0001", "공지사항을 성공적으로 생성했습니다."),
    NOTICE_FIND_SUCCESS("R-N-0002", "공지사항을 성공적으로 조회했습니다."),
    NOTICE_UPDATE_SUCCESS("R-N-0003", "공지사항을 성공적으로 수정했습니다."),
    NOTICE_DELETE_SUCCESS("R-N-0004", "공지사항을 성공적으로 삭제했습니다."),

    /**
     * NOTICE LIKE SUCCESS RESPONSE MESSAGE
     */
    NOTICE_LIKE_CREATE_SUCCESS("R-NL-0001", "공지사항 좋아요를 성공했습니다."),
    NOTICE_LIKE_FIND_SUCCESS("R-NL-0002", "공지사항 좋아요 조회를 성공했습니다."),
    NOTICE_LIKE_DELETE_SUCCESS("R-NL-0003", "공지사항 좋아요 삭제를 성공했습니다."),

    /**
     * NOTICE COMMENT SUCCESS RESPONSE MESSAGE
     */
    NOTICE_COMMENT_CREATE_SUCCESS("R-NC-0001", "공지사항 댓글 생성을 완료했습니다."),
    NOTICE_COMMENT_FIND_SUCCESS("R-NC-0002", "공지사항 댓글을 정상적으로 찾았습니다."),
    NOTICE_COMMENT_UPDATE_SUCCESS("R-NC-0003", "공지사항 댓글을 정상적으로 수정했습니다."),
    NOTICE_COMMENT_DELETE_SUCCESS("R-NC-0004", "공지사항 댓글을 정상적으로 삭제했습니다."),

    /**
     * FREE POST SUCCESS RESPONSE MASSAGE
     */
    FREE_POST_CREATE_SUCCESS("R-F-0001", "자유게시글을 성공적으로 생성했습니다."),
    FREE_POST_FIND_SUCCESS("R-F-0002", "자유게시글을 성공적으로 조회했습니다."),
    FREE_POST_UPDATE_SUCCESS("R-F-0003", "자유게시글을 성공적으로 수정했습니다."),
    FREE_POST_DELETE_SUCCESS("R-F-0004", "자유게시글을 성공적으로 삭제했습니다."),

    /**
     * FREE POST COMMENT SUCCESS RESPONSE MASSAGE
     */
    FREE_POST_COMMENT_CREATE_SUCCESS("R-FC-0001", "자유게시글 댓글을 성공적으로 생성했습니다."),
    FREE_POST_COMMENT_FIND_SUCCESS("R-FC-0002", "자유게시글 댓글을 성공적으로 조회했습니다."),
    FREE_POST_COMMENT_UPDATE_SUCCESS("R-FC-0003", "자유게시글 댓글을 성공적으로 수정했습니다."),
    FREE_POST_COMMENT_DELETE_SUCCESS("R-FC-0004", "자유게시글 댓글을 성공적으로 삭제했습니다."),

    /**
     * CORE QA SUCCESS RESPONSE MESSAGE
     **/
    CORE_QA_CREATE_SUCCESS("R-CQ-0001", "코어 QA 성공적으로 생성했습니다."),
    CORE_QA_READ_ALL_SUCCESS("R-CQ-0002", "코어 QA를 페이지 조회했습니다."),
    
    ;

    private final String code;
    private final String detail;
}