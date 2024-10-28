package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class SessionStudentTest {

    @DisplayName("유료강의는 최대수강인원을 초과할 수 없다")
    @Test
    void validate(){
        SessionStudent student = new SessionStudent(1);
        Pricing pricing = new Pricing(PricingType.PAID, 10000);

        student.addStudent(pricing, NsUserTest.JAVAJIGI);
        assertThatIllegalStateException()
                .isThrownBy(() -> student.addStudent(pricing, NsUserTest.SANJIGI))
                .withMessage("이 강의는 정원이 초과되었습니다.");
    }

    @DisplayName("수강신청 성공하면 유저를 등록한다")
    @Test
    void addStudent(){
        SessionStudent student = new SessionStudent(2);
        Pricing pricing = new Pricing(PricingType.PAID, 10000);

        student.addStudent(pricing, NsUserTest.JAVAJIGI);
        student.addStudent(pricing, NsUserTest.SANJIGI);

        assertThat(student.size()).isEqualTo(2);
    }
}
