package kr.ac.hs.selab.likes.dto;

import kr.ac.hs.selab.likes.domain.vo.TargetType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LikeCreateDto {
    private final String memberEmail;
    private final TargetType targetType;
    private final Long targetId;
}
