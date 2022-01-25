package kr.ac.hs.selab.member.domain;

import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import kr.ac.hs.selab.common.domain.BaseEntity;
import kr.ac.hs.selab.member.domain.vo.Avatar;
import kr.ac.hs.selab.member.domain.vo.Password;
import kr.ac.hs.selab.member.domain.vo.Terms;
import kr.ac.hs.selab.member.domain.vo.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Getter
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "member_email", unique = true)
    private String email;

    @Embedded
    private Password password;

    @Column(name = "member_student_id", unique = true)
    private String studentId;

    @Column(name = "member_name")
    private String name;

    @Getter
    @Column(name = "member_nickname", unique = true)
    private String nickname;

    @Column(name = "member_avatar")
    private Avatar avatar;

    @Embedded
    private Terms terms;
    /**
     * TODO :  TERMS 도메인 분리
     * 동의문은 따로 테이블을 가져가야.,,,
     * 테이블을 나눠서 가져가야! one to one이라도.. Member 만들때 넣어주기!
     * Terms는 테이블...컬럼...(회원가입인지, 동의문인지 enum으로 관리하기..)
     * 거래를 할 때도 Terms Check, 허가되징 낳은
     * 법 상으로 동의문을 저장하여 증명용으로 남겨야 될 때
     **/

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    private Member(String email, Password password, String studentId, String name,
        String nickname, Avatar avatar, Terms terms) {
        this.email = email;
        this.password = password;
        this.studentId = studentId;
        this.name = name;
        this.nickname = nickname;
        this.avatar = avatar;
        this.terms = terms;
        this.role = Role.USER;
    }

    public String getPasswordValue() {
        return password.getPassword();
    }

    public Collection<GrantedAuthority> getAuthority() {
        return Collections
            .singletonList(role.getGrantedAuthority());
    }
}