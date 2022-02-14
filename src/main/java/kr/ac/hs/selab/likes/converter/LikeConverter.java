package kr.ac.hs.selab.likes.converter;

import kr.ac.hs.selab.likes.domain.Likes;
import kr.ac.hs.selab.likes.dto.LikeCountDto;
import kr.ac.hs.selab.likes.dto.LikeCreateDto;
import kr.ac.hs.selab.likes.dto.response.LikeCountResponse;
import kr.ac.hs.selab.member.domain.Member;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class LikeConverter {
    public Likes toLike(LikeCreateDto dto, Member member) {
        return Likes.builder()
                .member(member)
                .targetType(dto.getTargetType())
                .targetId(dto.getTargetId())
                .build();
    }

    public LikeCountResponse toLikeResponse(LikeCountDto dto, List<Likes> likes) {
        return LikeCountResponse.builder()
                .targetType(dto.getTargetType())
                .targetId(dto.getTargetId())
                .likeCount((long) likes.size())
                .build();
    }
}
