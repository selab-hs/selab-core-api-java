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
import kr.ac.hs.selab.post.dto.response.PostFindByBoardResponse;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        Post post = postService.create(postDto, member, board);
        return new PostResponse(post.getId());
    }

    public PostFindByBoardResponse findPostsResponseByBoardId(Long boardId) {
        Board board = boardService.findById(boardId);
        Long totalCount = postService.count(board);
        List<Post> posts = postService.findPostsByBoard(board);

        return PostConverter.toPostFindByBoardResponse(board.getId(), totalCount, posts);
    }

    @Transactional
    public PostResponse delete(Long postId) {
        Post post = postService.delete(postId);
        publisher.publishEvent(PostEvent.of(post));

        return new PostResponse(post.getId());
    }
}
