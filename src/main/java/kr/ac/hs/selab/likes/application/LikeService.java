package kr.ac.hs.selab.likes.application;

import kr.ac.hs.selab.likes.converter.LikeConverter;
import kr.ac.hs.selab.likes.domain.Likes;
import kr.ac.hs.selab.likes.dto.LikeCountDto;
import kr.ac.hs.selab.likes.dto.LikeCreateDto;
import kr.ac.hs.selab.likes.dto.response.LikeCountResponse;
import kr.ac.hs.selab.likes.dto.response.LikeResponse;
import kr.ac.hs.selab.likes.infrastructure.LikeRepository;
import kr.ac.hs.selab.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public LikeResponse createByLikeCreateDtoAndMember(LikeCreateDto dto, Member member) {
        Likes like = likeRepository.save(LikeConverter.toLike(dto, member));
        return new LikeResponse(like.getId());
    }

    public LikeCountResponse findLikeCountResponseByLikeCountDto(LikeCountDto dto) {
        List<Likes> likes = likeRepository.findByTargetTypeAndTargetId(dto.getTargetType(), dto.getTargetId());
        return LikeConverter.toLikeResponse(dto, likes);
    }

    @Transactional
    public LikeResponse deleteById(Long id) {
        likeRepository.deleteById(id);
        return new LikeResponse(id);
    }
}
