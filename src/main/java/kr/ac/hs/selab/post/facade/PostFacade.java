package kr.ac.hs.selab.post.facade;

import kr.ac.hs.selab.board.application.BoardService;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.board.domain.BoardEvent;
import kr.ac.hs.selab.comment.application.CommentService;
import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.application.PostService;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostCreateDto;
import kr.ac.hs.selab.post.dto.response.PostFindByBoardResponse;
import kr.ac.hs.selab.post.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class PostFacade {
    private final ApplicationEventPublisher publisher;
    private final MemberService memberService;
    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public PostResponse create(PostCreateDto postDto) {
        Member member = memberService.findByEmail(postDto.getMemberEmail());
        Board board = boardService.findBoardById(postDto.getBoardId());

        return postService.create(postDto, member, board);
    }

    public PostFindByBoardResponse findPostsResponseByBoardId(Long boardId) {
        Board board = boardService.findBoardById(boardId);
        return postService.findPostsResponseByBoard(board);
    }

    public PostResponse delete(Long postId) {
        Post post = postService.delete(postId);
        commentService.deleteByPost(post);
        return new PostResponse(post.getId());
    }
}
