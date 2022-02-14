package kr.ac.hs.selab.board.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class BoardEvent {
    Board board;
}
