package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}