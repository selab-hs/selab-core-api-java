package kr.ac.hs.selab.board.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class BoardEvent {
    private final Board board;
}
