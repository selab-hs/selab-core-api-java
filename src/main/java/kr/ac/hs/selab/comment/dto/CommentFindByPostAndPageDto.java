package kr.ac.hs.selab.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Getter
public class CommentFindByPostAndPageDto {
    private final Long postId;
    private final Pageable pageable;
}
