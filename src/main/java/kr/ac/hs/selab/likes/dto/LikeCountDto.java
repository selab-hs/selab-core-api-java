package kr.ac.hs.selab.likes.dto;

import kr.ac.hs.selab.likes.domain.vo.TargetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LikeCountDto {
    private final TargetType targetType;
    private final Long targetId;
}
