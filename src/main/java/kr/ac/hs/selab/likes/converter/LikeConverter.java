package kr.ac.hs.selab.likes.converter;

import kr.ac.hs.selab.likes.domain.Likes;
import kr.ac.hs.selab.likes.dto.LikeCreateDto;
import kr.ac.hs.selab.likes.dto.response.LikeResponse;
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

    public LikeResponse toLikeResponse(List<Likes> likes) {
        Likes firstLike = likes.get(0);

        return LikeResponse.builder()
                .targetType(firstLike.getTargetType())
                .id(firstLike.getTargetId())
                .likeCount((long) likes.size())
                .build();
    }
}
