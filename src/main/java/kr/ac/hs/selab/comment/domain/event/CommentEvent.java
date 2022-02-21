package kr.ac.hs.selab.comment.domain.event;

import kr.ac.hs.selab.comment.domain.Comment;
import lombok.Value;

@Value(staticConstructor = "of")
public class CommentEvent {
    Comment comment;
}
