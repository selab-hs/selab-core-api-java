package kr.ac.hs.selab.board.presentation;

import kr.ac.hs.selab.board.converter.BoardConverter;
import kr.ac.hs.selab.board.dto.BoardCreateDto;
import kr.ac.hs.selab.board.dto.request.BoardRequest;
import kr.ac.hs.selab.board.dto.response.BoardFindAllResponse;
import kr.ac.hs.selab.board.dto.response.BoardFindResponse;
import kr.ac.hs.selab.board.dto.response.BoardResponse;
import kr.ac.hs.selab.board.facade.BoardFacade;
import kr.ac.hs.selab.common.template.ResponseMessage;
import kr.ac.hs.selab.common.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/boards")
@RestController
public class BoardController implements BoardSdk {
    private final BoardFacade boardFacade;

    @Override
    @PostMapping
    public ResponseTemplate<BoardResponse> create(@Validated @RequestBody BoardRequest request) {
        var dto = new BoardCreateDto(request.getTitle(), request.getDescription());
        var response = boardFacade.create(dto);
        return ResponseTemplate.created(ResponseMessage.BOARD_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseTemplate<BoardFindResponse> find(@PathVariable Long id) {
        var response = boardFacade.findBoardResponseById(id);
        return ResponseTemplate.ok(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping
    public ResponseTemplate<BoardFindAllResponse> findAll() {
        var response = boardFacade.findBoardFindAllResponse();
        return ResponseTemplate.ok(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseTemplate<BoardResponse> update(@PathVariable Long id,
                                                  @Validated @RequestBody BoardRequest request) {
        var dto = BoardConverter.toBoardUpdateDto(id, request);
        var response = boardFacade.update(dto);
        return ResponseTemplate.ok(ResponseMessage.BOARD_UPDATE_SUCCESS, response);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseTemplate<BoardResponse> delete(@PathVariable Long id) {
        var response = boardFacade.delete(id);
        return ResponseTemplate.ok(ResponseMessage.BOARD_DELETE_SUCCESS, response);
    }
}