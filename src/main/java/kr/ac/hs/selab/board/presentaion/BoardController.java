package kr.ac.hs.selab.board.presentaion;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.request.BoardRequest;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.dto.response.BoardsResponse;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(value = "api/v1/admin/boards")
@RestController
public class BoardController implements BoardSdk {

    private final BoardService boardService;

    @Override
    @PostMapping
    public ResponseTemplate<BoardResponse> create(@Valid @RequestBody BoardRequest request) {
        BoardCreateDto dto = BoardCreateDto.of(request.getTitle(), request.getDescription());
        BoardResponse response = boardService.create(dto);
        return ResponseTemplate.created(ResponseMessage.BOARD_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseTemplate<BoardResponse> find(@PathVariable Long id) {
        BoardResponse response = boardService.find(id);
        return ResponseTemplate.ok(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping
    public ResponseTemplate<BoardsResponse> findAll() {
        BoardsResponse response = boardService.findAll();
        return ResponseTemplate.ok(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseTemplate<BoardResponse> update(@PathVariable Long id,
        @Valid @RequestBody BoardRequest request) {
        BoardUpdateDto dto = BoardConverter.toBoardUpdateDto(id, request);
        BoardResponse response = boardService.update(dto);
        return ResponseTemplate.ok(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseTemplate<BoardResponse> delete(@PathVariable Long id) {
        BoardResponse response = boardService.delete(id);
        return ResponseTemplate.ok(ResponseMessage.BOARD_DELETE_SUCCESS, response);
    }
}