package kr.ac.hs.selab.board.presentaion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kr.ac.hs.selab.board.dto.request.BoardRequest;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.dto.response.BoardsResponse;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "Board REST API", description = "게시판 api")
public interface BoardSwaggerController {
    @Operation(summary = "게시판 생성", description = "게시판 정보를 이용해서 게시판을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 생성 성공"),
            @ApiResponse(code = 400, message = "게시판 생성 실패")
    })
    ResponseEntity<ResponseTemplate<BoardResponse>> create(@Parameter(description = "게시판 정보") BoardRequest request);

    @Operation(summary = "전체 게시판 조회", description = "전체 게시판을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 조회 성공"),
            @ApiResponse(code = 400, message = "게시판 조회 실패")
    })
    ResponseEntity<ResponseTemplate<BoardsResponse>> findAll();

    @Operation(summary = "게시판 조회", description = "게시판 정보를 이용해서 게시판을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 조회 성공"),
            @ApiResponse(code = 400, message = "게시판 조회 실패")
    })
    ResponseEntity<ResponseTemplate<BoardResponse>> find(@Parameter(description = "게시판 id 값") Long id);

    @Operation(summary = "게시판 수정", description = "새로운 게시판 정보를 이용해서 게시판을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 수정 성공"),
            @ApiResponse(code = 400, message = "게시판 수정 실패")
    })
    ResponseEntity<ResponseTemplate<BoardResponse>> update(@Parameter(description = "게시판 id 값") Long id,
                                                           @Parameter(description = "게시판 정보") BoardRequest request);

    @Operation(summary = "게시판 삭제", description = "게시판을 지정하여 게시판을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "게시판 삭제 성공"),
            @ApiResponse(code = 400, message = "게시판 삭제 실패")
    })
    ResponseEntity<ResponseTemplate<BoardResponse>> delete(@Parameter(description = "게시판 id 값") Long id);
}
