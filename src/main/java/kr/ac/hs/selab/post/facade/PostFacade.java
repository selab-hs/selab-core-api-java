package kr.ac.hs.selab.post.facade;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.domain.event.PostEvent;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostFindByBoardAndPageDto;
import kr.ac.hs.selab.post.dto.response.PostFindByBoardIdAndPageResponse;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class PostFacade {
    private final ApplicationEventPublisher publisher;
    private final MemberService memberService;
    private final BoardService boardService;
    private final PostService postService;

    @Transactional
    public PostResponse create(PostCreateDto postDto) {
        Member member = memberService.findByEmail(postDto.getMemberEmail());
        Board board = boardService.findById(postDto.getBoardId());

        Post post = postService.create(postDto, member.getId(), board.getId());
        return new PostResponse(post.getId());
    }

    public PostFindByBoardIdAndPageResponse findPostsResponseByBoardId(PostFindByBoardAndPageDto dto) {
        Board board = boardService.findById(dto.getBoardId());
        Long totalCount = postService.count(board.getId());
        Page<Post> posts = postService.findPostsByBoardIdAndPage(board.getId(), dto.getPageable());

        return PostConverter.toPostFindByBoardResponse(dto, totalCount, posts);
    }

    @Transactional
    public PostResponse delete(Long postId) {
        Post post = postService.delete(postId);
        publisher.publishEvent(PostEvent.of(post));

        return new PostResponse(post.getId());
    }
}
