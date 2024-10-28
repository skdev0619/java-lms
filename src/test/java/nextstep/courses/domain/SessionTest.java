package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class SessionTest {

    @DisplayName("강의 상태가 모집 중이 아니면 수강 신청 할 수 없다")
    @Test
    void validateSessionStatus() {
        Session session = createSession(SessionStatus.FINISHED, PricingType.PAID, 10000, 50);

        assertThatIllegalStateException()
                .isThrownBy(() -> session.enrollStudent(NsUserTest.JAVAJIGI, 10000))
                .withMessage("강의 상태가 모집 중이 아니면 수강 신청 할 수 없습니다.");
    }


    @DisplayName("유료강의는 정원 초과되면 수강할 수 없다")
    @Test
    void validateEnrollmentLimit() {
        Session session = createSession(SessionStatus.RECRUITING, PricingType.PAID, 10000, 1);
        session.enrollStudent(NsUserTest.JAVAJIGI, 10000);

        assertThatIllegalStateException()
                .isThrownBy(() -> session.enrollStudent(NsUserTest.SANJIGI, 10000))
                .withMessage("이 강의는 정원이 초과되었습니다.");
    }

    @DisplayName("유료강의는 지불한 돈이 강의 가격보다 많으면 수강 신청 할 수 없다")
    @Test
    void overPay() {
        Session session = createSession(SessionStatus.RECRUITING, PricingType.PAID, 10000, 50);

        assertThatIllegalStateException()
                .isThrownBy(() -> session.enrollStudent(NsUserTest.JAVAJIGI, 10001))
                .withMessage("강의 가격보다 지불한 돈이 더 많습니다.");
    }

    @DisplayName("유료강의는 강의 가격보다 지불한 돈이 적으면 수강 신청 할 수 없다")
    @Test
    void lessPay() {
        Session session = createSession(SessionStatus.RECRUITING, PricingType.PAID, 10000, 50);

        assertThatIllegalStateException()
                .isThrownBy(() -> session.enrollStudent(NsUserTest.JAVAJIGI, 9999))
                .withMessage("수강 신청하기에 지불한 돈이 부족합니다.");

    }

    private Session createSession(SessionStatus status, PricingType type, int price, int enrollmentLimit) {
        SessionPeriod dateRange = new SessionPeriod(
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 10, 5, 23, 59)
        );
        Image image = new Image(new byte[]{}, new Size(300, 200), "jpg");
        return new Session(dateRange, status, image, type, price, enrollmentLimit);
    }

}