package kr.ac.hs.selab.free_post.dto;

import kr.ac.hs.selab.free_post.domain.FreePost;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Builder
@Getter
public class FreePostFindByPageDto {
    private final Long totalCount;
    private final Pageable pageable;
    private final Page<FreePost> freePosts;
}
