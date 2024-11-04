package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class SessionStudentTest {

    @DisplayName("동일한 유저는 중복으로 수강신청할 수 없다.")
    @Test
    void checkExistingStudent() {
        SessionStudents student = new SessionStudents(1L, List.of(1L));
        Pricing pricing = new Pricing(PricingType.PAID, 10000);

        assertThatIllegalStateException()
                .isThrownBy(() -> student.addStudent(NsUserTest.JAVAJIGI))
                .withMessage("이미 수강신청한 유저입니다.");
    }

    @DisplayName("수강신청 성공하면 유저를 등록한다")
    @Test
    void addStudent() {
        SessionStudents student = new SessionStudents(1L);
        Pricing pricing = new Pricing(PricingType.PAID, 10000);

        student.addStudent(NsUserTest.JAVAJIGI);
        student.addStudent(NsUserTest.SANJIGI);

        assertThat(student.size()).isEqualTo(2);
    }

}
