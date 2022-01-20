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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(value = "api/v1/boards")
@RestController
public class BoardController implements BoardSwaggerController {
    private final BoardService boardService;

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseTemplate<BoardResponse>> create(@Valid @RequestBody BoardRequest request) {
        BoardCreateDto dto = BoardCreateDto.of(request.getTitle(), request.getDescription());
        BoardResponse response = boardService.create(dto);
        return ResponseTemplate.of(ResponseMessage.BOARD_CREATE_SUCCESS, response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplate<BoardResponse>> find(@PathVariable Long id) {
        BoardResponse response = boardService.find(id);
        return ResponseTemplate.of(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseTemplate<BoardsResponse>> findAll() {
        BoardsResponse response = boardService.findAll();
        return ResponseTemplate.of(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTemplate<BoardResponse>> update(@PathVariable Long id,
                                                                  @Valid @RequestBody BoardRequest request) {
        BoardUpdateDto dto = BoardConverter.toBoardUpdateDto(id, request);
        BoardResponse response = boardService.update(dto);
        return ResponseTemplate.of(ResponseMessage.BOARD_FIND_SUCCESS, response);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseTemplate<BoardResponse>> delete(@PathVariable Long id) {
        BoardResponse response = boardService.delete(id);
        return ResponseTemplate.of(ResponseMessage.BOARD_DELETE_SUCCESS, response);
    }
}
