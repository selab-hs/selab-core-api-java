package kr.ac.hs.selab.board.domain.event;

import kr.ac.hs.selab.board.domain.Post;
import lombok.Value;

@Value(staticConstructor = "of")
public class PostEvent {
    Post post;
}