package kr.ac.hs.selab.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BoardCreateDto {
    private String title;
    private String description;

    public static BoardCreateDto of(String title, String description) {
        return new BoardCreateDto(title, description);
    }
}
