package kr.ac.hs.selab.post.domain.event;

import kr.ac.hs.selab.post.domain.Post;
import lombok.Value;

@Value(staticConstructor = "of")
public class PostEvent {
    Post post;
}
