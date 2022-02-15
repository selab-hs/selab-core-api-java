package kr.ac.hs.selab.board.domain.event;

import kr.ac.hs.selab.board.domain.Board;
import lombok.Value;

@Value(staticConstructor = "of")
public class BoardEvent {
    Board board;
}
