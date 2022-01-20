package kr.ac.hs.selab.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BoardCreateDto {
    private final String title;
    private final String description;

    public static BoardCreateDto of(String title, String description) {
        return new BoardCreateDto(title, description);
    }
}
