package kr.ac.hs.selab.likes.dto.response;

import kr.ac.hs.selab.likes.domain.vo.TargetType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LikeResponse {
    private final TargetType targetType;
    private final Long id;
    private final Long likeCount;
}
