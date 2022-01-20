package kr.ac.hs.selab.member.application;

import kr.ac.hs.selab.error.exception.common.NonExitsException;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.DuplicationException;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.dto.bundle.MemberCreateBundle;
import kr.ac.hs.selab.member.dto.response.MemberCreateResponse;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberCreateResponse create(MemberCreateBundle bundle) {
        isDuplication(bundle);
        Member instance = MemberConverter.toMember(bundle, passwordEncoder);
        Member member = memberRepository.save(instance);
        return MemberConverter.toCreateMemberResponse(member);
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new NonExitsException(ErrorMessage.MEMBER_NOT_EXISTS_ERROR));
    }

    private void isDuplication(MemberCreateBundle bundle) {
        if (existsByEmail(bundle.getEmail())) {
            throw new DuplicationException(ErrorMessage.MEMBER_EMAIL_DUPLICATION_ERROR);
        }
        if (existsByStudentId(bundle.getStudentId())) {
            throw new DuplicationException(ErrorMessage.MEMBER_STUDENT_ID_DUPLICATION_ERROR);
        }
        if (existsByNickname(bundle.getNickname())) {
            throw new DuplicationException(ErrorMessage.MEMBER_NICKNAME_DUPLICATION_ERROR);
        }
    }

    private boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    private boolean existsByStudentId(String studentId) {
        return memberRepository.existsByStudentId(studentId);
    }

    private boolean existsByNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}