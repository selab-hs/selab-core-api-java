package kr.ac.hs.selab.auth.application;

import kr.ac.hs.selab.auth.converter.AuthConverter;
import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // TODO : ERROR를 Spring Message로 바꾸기!
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) {
        return memberRepository.findByEmail(email)
            .map(AuthConverter::toUser)
            .orElseThrow(() -> new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR));
    }
}