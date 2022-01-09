package kr.ac.hs.selab.auth.application;

import kr.ac.hs.selab.auth.converter.AuthConverter;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.member.domain.vo.Email;
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
    public UserDetails loadUserByUsername(final String username) {
        Email email = Email.of(username);
        return memberRepository.findByEmail(email)
            .map(authConverter::toUser)
            .orElseThrow(() -> new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR));
    }
}