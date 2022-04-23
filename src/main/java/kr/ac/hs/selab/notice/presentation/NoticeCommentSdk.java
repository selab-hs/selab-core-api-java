package kr.ac.hs.selab.notice.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.SwaggerNote;
import kr.ac.hs.selab.notice.dto.request.NoticeCommentRequest;
import kr.ac.hs.selab.notice.dto.response.NoticeCommentFindByNoticeIdAndPageResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeCommentFindResponse;
import kr.ac.hs.selab.notice.dto.response.NoticeCommentResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "공지사항 댓글 API", description = "Notice Comment Controller (MVP)")
public interface NoticeCommentSdk {
    @ApiOperation(value = "공지사항 댓글 생성", notes = SwaggerNote.NOTICE_COMMENT_CREATE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 댓글 생성 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 생성 실패")
    })
    ResponseTemplate<NoticeCommentResponse> create(@Parameter(description = "공지사항 id 값") Long noticeId,
                                                   @Parameter(description = "공지사항 댓글 정보") NoticeCommentRequest request);

    @ApiOperation(value = "공지사항 댓글 조회", notes = SwaggerNote.NOTICE_COMMENT_FIND)
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 댓글 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 조회 실패")
    })
    ResponseTemplate<NoticeCommentFindResponse> findByNoticeCommentId(@Parameter(description = "공지사항 댓글 id 값") Long noticeId);

    @ApiOperation(value = "공지사항의 전체 댓글 조회", notes = SwaggerNote.NOTICE_COMMENT_FIND_BY_PAGE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 댓글 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 조회 실패")
    })
    ResponseTemplate<NoticeCommentFindByNoticeIdAndPageResponse> findByNoticeIdAndPage(@Parameter(description = "공지사항 id 값") Long noticeId,
                                                                                       @Parameter(description = "페이지 정보") Pageable pageable);

    @ApiOperation(value = "공지사항 수정", notes = SwaggerNote.NOTICE_COMMENT_UPDATE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 댓글 수정 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 수정 실패")
    })
    ResponseTemplate<NoticeCommentResponse> update(@Parameter(description = "공지사항 댓글 id 값") Long commentId,
                                                   @Parameter(description = "공지사항 댓글 정보") NoticeCommentRequest request);

    @ApiOperation(value = "공지사항 삭제", notes = SwaggerNote.NOTICE_COMMENT_DELETE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 댓글 삭제 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 삭제 실패")
    })
    ResponseTemplate<NoticeCommentResponse> delete(@Parameter(description = "공지사항 댓글 id 값") Long noticeCommentId);
}