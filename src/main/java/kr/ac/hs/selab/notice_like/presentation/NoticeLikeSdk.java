package kr.ac.hs.selab.notice_like.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import kr.ac.hs.selab.common.template.SwaggerNote;
import kr.ac.hs.selab.notice_like.dto.response.NoticeLikeFindResponse;
import kr.ac.hs.selab.notice_like.dto.response.NoticeLikeResponse;
import org.springframework.web.bind.annotation.PathVariable;

@Api(tags = "공지사항 좋아요")
public interface NoticeLikeSdk {
    @ApiOperation(value = "공지사항 좋아요 생성", notes = SwaggerNote.NOTICE_LIKE_CREATE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 좋아요 성공"),
            @ApiResponse(code = 400, message = "공지사항 좋아요 실패")
    })
    ResponseTemplate<NoticeLikeResponse> create(@PathVariable Long noticeId);

    @ApiOperation(value = "공지사항에 모든 좋아요 조회", notes = SwaggerNote.NOTICE_LIKE_FIND)
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 좋아요 조회 성공"),
            @ApiResponse(code = 400, message = "공지사항 좋아요 조회 실패")
    })
    ResponseTemplate<NoticeLikeFindResponse> find(@PathVariable Long noticeId);

    @ApiOperation(value = "공지사항 좋아요 삭제", notes = SwaggerNote.NOTICE_LIKE_DELETE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "공지사항 좋아요 삭제 성공"),
            @ApiResponse(code = 400, message = "공지사항 좋아요 삭제 실패")
    })
    ResponseTemplate<NoticeLikeResponse> delete(@PathVariable Long noticeId);
}
