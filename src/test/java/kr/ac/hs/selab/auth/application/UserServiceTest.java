package kr.ac.hs.selab.auth.application;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private UserService userService;

    private final String email = "wrjs@naver.com";

    @Test
    void loadUserByUsername_인증_인가() {
        // given
        Member member = Member.builder()
                .email(email)
                .password(new Password("!Comwldskjfo123d4"))
                .build();

        when(memberRepository.findByEmail(any()))
                .thenReturn(Optional.ofNullable(member));

        // when
        UserDetails userDetails = userService.loadUserByUsername(email);

        // then
        assertEquals(member.getEmail(), userDetails.getUsername());
    }
}