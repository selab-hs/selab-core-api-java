package kr.ac.hs.selab.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Getter
public class PostFindByBoardAndPageDto {
    private final Long boardId;
    private final Pageable pageable;
}