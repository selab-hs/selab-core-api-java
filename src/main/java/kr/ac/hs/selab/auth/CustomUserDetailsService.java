package kr.ac.hs.selab.auth;

import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AuthConverter authConverter;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String inputEmail) {
        Email email = new Email(inputEmail);
        return memberRepository.findOneWithAuthoritiesByEmail(email)
            .map(authConverter::toUser)
            .orElseThrow(() -> new AuthException(ErrorMessage.NOT_EXIST_MEMBER));
    }
}