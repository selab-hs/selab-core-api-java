package kr.ac.hs.selab.free_post.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Getter
public class FreePostCommentFindByFreePostIdAndPageDto {
    private final Long freePostId;
    private final Pageable pageable;
}
