package kr.ac.hs.selab.likes.infrastructure;

import kr.ac.hs.selab.likes.domain.Likes;
import kr.ac.hs.selab.likes.domain.vo.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByTargetTypeAndTargetId(TargetType targetType, Long targetId);
}
