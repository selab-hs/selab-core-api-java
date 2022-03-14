package kr.ac.hs.selab.post.facade;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.converter.PostConverter;
import kr.ac.hs.selab.post.domain.event.PostEvent;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.PostFindByBoardAndPageDto;
import kr.ac.hs.selab.post.dto.PostUpdateDto;
import kr.ac.hs.selab.post.dto.response.PostFindByBoardIdAndPageResponse;
import kr.ac.hs.selab.post.dto.response.PostFindResponse;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
        var member = memberService.findByEmail(postDto.getMemberEmail());
        var board = boardService.findById(postDto.getBoardId());

        var post = postService.create(postDto, member.getId(), board.getId());
        return new PostResponse(post.getId());
    }

    public PostFindResponse findPostResponseById(Long id) {
        var post = postService.findPostById(id);
        return PostConverter.toPostFindResponse(post);
    }

    public PostFindByBoardIdAndPageResponse findPostsResponseByBoardId(PostFindByBoardAndPageDto dto) {
        var board = boardService.findById(dto.getBoardId());
        var totalCount = postService.count(board.getId());
        var posts = postService.findPostsByBoardIdAndPage(board.getId(), dto.getPageable());

        return PostConverter.toPostFindByBoardResponse(dto, totalCount, posts);
    }

    @Transactional
    public PostResponse update(PostUpdateDto dto) {
        var post = postService.update(dto);
        return new PostResponse(post.getId());
    }

    @Transactional
    public PostResponse delete(Long postId) {
        var post = postService.deleteById(postId);
        publisher.publishEvent(PostEvent.of(post));

        return new PostResponse(post.getId());
    }
}
