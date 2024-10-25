package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class EnrollmentTest {

    @DisplayName("강의 상태가 모집 중이 아니면 수강 신청 할 수 없다")
    @Test
    void validateSessionStatus(){
        Session session = createSession(SessionStatus.FINISHED, PricingType.PAID, 10000, 50);
        Enrollment enrollment = new Enrollment();

        assertThatIllegalStateException()
                        .isThrownBy(() -> enrollment.register(session))
                        .withMessage("강의 상태가 모집 중이 아니면 수강 신청 할 수 없습니다.");
    }

    private Session createSession(SessionStatus status, PricingType type, int price, int enrollmentLimit){
        DateRange dateRange = new DateRange(
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 10, 5, 23, 59)
        );
        Image image = new Image(new byte[]{}, new Size(300, 200), "jpg");
        return new Session(dateRange, status, image, type, price, enrollmentLimit);
    }
}
