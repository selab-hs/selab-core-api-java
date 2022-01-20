package kr.ac.hs.selab.board.dto;

import lombok.Getter;

@Getter
public class BoardCreateDto {
    private String title;
    private String description;

    private BoardCreateDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static BoardCreateDto of(String title, String description) {
        return new BoardCreateDto(title, description);
    }
}
