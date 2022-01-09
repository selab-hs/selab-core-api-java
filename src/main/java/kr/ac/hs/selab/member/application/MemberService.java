package kr.ac.hs.selab.member.application;

import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.DuplicationException;
import kr.ac.hs.selab.member.converter.MemberConverter;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Nickname;
import kr.ac.hs.selab.member.domain.vo.StudentId;
import kr.ac.hs.selab.member.dto.bundle.CreateMemberBundle;
import kr.ac.hs.selab.member.dto.response.CreateMemberResponse;
import kr.ac.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateMemberResponse create(CreateMemberBundle bundle) {
        isDuplication(bundle);
        Member instance = memberConverter.toMember(bundle, passwordEncoder);
        Member member = memberRepository.save(instance);
        return memberConverter.toCreateMemberResponse(member);
    }

    @Transactional(readOnly = true)
    public Member findByEmail(Email email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("hell"));
    }

    private void isDuplication(CreateMemberBundle bundle) {
        isDuplicationEmail(bundle.getEmail());
        isDuplicationStudentId(bundle.getStudentId());
        isDuplicationNickname(bundle.getNickname());
    }

    private void isDuplicationEmail(Email email) {
        if (existsByEmail(email)) {
            throw new DuplicationException(ErrorMessage.MEMBER_EMAIL_DUPLICATION_ERROR);
        }
    }

    private void isDuplicationStudentId(StudentId studentId) {
        if (existsByStudentId(studentId)) {
            throw new DuplicationException(ErrorMessage.MEMBER_STUDENT_ID_DUPLICATION_ERROR);
        }
    }

    private void isDuplicationNickname(Nickname nickname) {
        if (existsByNickname(nickname)) {
            throw new DuplicationException(ErrorMessage.MEMBER_NICKNAME_DUPLICATION_ERROR);
        }
    }

    private boolean existsByEmail(Email email) {
        return memberRepository.existsByEmail(email);
    }

    private boolean existsByStudentId(StudentId studentId) {
        return memberRepository.existsByStudentId(studentId);
    }

    private boolean existsByNickname(Nickname nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}