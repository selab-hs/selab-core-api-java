package kr.ac.hs.selab.board.presentation;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.BoardUpdateDto;
import kr.ac.hs.selab.board.dto.request.BoardRequest;
import kr.ac.hs.selab.board.dto.response.BoardFindAllResponse;
import kr.ac.hs.selab.board.dto.response.BoardFindResponse;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.facade.BoardFacade;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("api/v1/admin/boards")
@RestController
public class BoardController implements BoardSdk {
    private final BoardService boardService;
    private final BoardFacade boardFacade;

    @Override
    @PostMapping
    public ResponseTemplate<BoardResponse> create(@Valid @RequestBody BoardRequest request) {
        BoardCreateDto dto = new BoardCreateDto(request.getTitle(), request.getDescription());
        BoardResponse response = boardFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.BOARD_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseTemplate<BoardFindResponse> find(@PathVariable Long id) {
        BoardFindResponse response = boardFacade.findBoardResponseById(id);
        return ResponseTemplate.ok(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping
    public ResponseTemplate<BoardFindAllResponse> findAll() {
        BoardFindAllResponse response = boardFacade.findBoardFindAllResponse();
        return ResponseTemplate.ok(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseTemplate<BoardResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody BoardRequest request) {
        BoardUpdateDto dto = BoardConverter.toBoardUpdateDto(id, request);
        BoardResponse response = boardFacade.update(dto);
        return ResponseTemplate.ok(ResponseMessage.BOARD_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseTemplate<BoardResponse> delete(@PathVariable Long id) {
        BoardResponse response = boardFacade.delete(id);
        return ResponseTemplate.ok(ResponseMessage.BOARD_DELETE_SUCCESS, response);
    }
}