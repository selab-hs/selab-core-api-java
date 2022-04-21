package kr.ac.hs.selab.member.facade;

import kr.ac.hs.selab.member.application.MemberService;
import kr.ac.hs.selab.member.dto.request.MemberExistRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class MemberFacadeTest {
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberFacade memberFacade;

    @Test
    public void 회원가입_여부_조회() {
        // given
        var request = new MemberExistRequest("leeheefull@gmail.com");

        // mock
        Mockito.when(memberService.existsByEmail(anyString()))
                .thenReturn(true);

        // when
        var actual = memberFacade.exist(request);

        // then
        assertTrue(actual.isSignup());
    }
}
