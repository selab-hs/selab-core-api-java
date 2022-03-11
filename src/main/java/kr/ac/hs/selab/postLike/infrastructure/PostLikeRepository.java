package kr.ac.hs.selab.postLike.infrastructure;

import kr.ac.hs.selab.postLike.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findByPostId(Long postId);
}
