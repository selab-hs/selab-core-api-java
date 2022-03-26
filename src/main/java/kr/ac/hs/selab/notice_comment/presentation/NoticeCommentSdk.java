package kr.ac.hs.selab.notice_comment.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.notice_comment.dto.request.NoticeCommentRequest;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentFindByNoticeIdAndPageResponse;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentFindResponse;
import kr.ac.hs.selab.notice_comment.dto.response.NoticeCommentResponse;
import org.springframework.data.domain.Pageable;

@Api(tags = "Notice Comment REST API", description = "공지사항 댓글 api")
public interface NoticeCommentSdk {
    @Operation(summary = "공지사항 댓글 생성", description = "공지사항 댓글 정보를 이용해서 댓글을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 댓글 생성 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 생성 실패")
    })
    ResponseTemplate<NoticeCommentResponse> create(@Parameter(description = "공지사항 id 값") Long noticeId,
                                                   @Parameter(description = "공지사항 댓글 정보") NoticeCommentRequest request);

    @Operation(summary = "공지사항 댓글 조회", description = "공지사항 댓글 정보를 이용해서 댓글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 댓글 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 조회 실패")
    })
    ResponseTemplate<NoticeCommentFindResponse> findByNoticeCommentId(@Parameter(description = "공지사항 댓글 id 값") Long noticeId);

    @Operation(summary = "공지사항의 전체 댓글 조회", description = "공지사항에 해당하는 전체 댓글을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 댓글 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 조회 실패")
    })
    ResponseTemplate<NoticeCommentFindByNoticeIdAndPageResponse> findByNoticeIdAndPage(@Parameter(description = "공지사항 id 값") Long noticeId,
                                                                                       @Parameter(description = "페이지 정보") Pageable pageable);

    @Operation(summary = "공지사항 수정", description = "변경할 공지사항 댓글 정보를 이용해서 댓글을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 댓글 수정 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 수정 실패")
    })
    ResponseTemplate<NoticeCommentResponse> update(@Parameter(description = "공지사항 댓글 id 값") Long commentId,
                                                   @Parameter(description = "공지사항 댓글 정보") NoticeCommentRequest request);

    @Operation(summary = "공지사항 삭제", description = "공지사항 댓글을 지정하여 댓글을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 댓글 삭제 성공"),
            @ApiResponse(code = 400, message = "공지사항 댓글 삭제 실패")
    })
    ResponseTemplate<NoticeCommentResponse> delete(@Parameter(description = "공지사항 댓글 id 값") Long noticeCommentId);
}
