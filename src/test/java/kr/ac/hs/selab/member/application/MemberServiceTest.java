package kr.ac.hs.selab.member.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private static final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .defaultGenerator(FieldReflectionArbitraryGenerator.INSTANCE)
            .nullInject(0)
            .build();

    @Test
    public void 회원_생성하기() {
        // given
        var expected = fixtureMonkey.giveMeOne(Member.class);

        // mock
        Mockito.when(memberRepository.save(any()))
                .thenReturn(expected);

        // when
        var actual = memberService.save(expected);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 회원_조회하기() {
        // given
        var email = fixtureMonkey.giveMeOne(String.class);
        var expected = fixtureMonkey.giveMeBuilder(Member.class)
                .set("email", email)
                .sample();

        // mock
        Mockito.when(memberRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(expected));

        // when
        var actual = memberService.findByEmail(email);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void 회원_가입_여부_확인하기() {
        // given
        var email = fixtureMonkey.giveMeOne(String.class);

        // mock
        Mockito.when(memberRepository.existsByEmail(anyString()))
                .thenReturn(true);

        // when
        var actual = memberService.existsByEmail(email);

        // then
        assertTrue(actual);
    }
}
