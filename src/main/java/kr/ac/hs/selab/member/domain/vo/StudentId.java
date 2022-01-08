package kr.ac.hs.selab.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import kr.ac.hs.selab.error.template.ErrorMessage;
import kr.ac.hs.selab.error.exception.common.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentId {

    @Column(name = "member_student_id", unique = true)
    private String studentId;

    private StudentId(String studentId) {
        validate(studentId);
        this.studentId = studentId;
    }

    private void validate(String studentId) {
        if (!StringUtils.hasText(studentId)) {
            throw new InvalidArgumentException(
                ErrorMessage.MEMBER_STUDENT_ID_INVALID_ARGUMENT_ERROR);
        }
    }

    public static StudentId of(String studentId) {
        return new StudentId(studentId);
    }
}