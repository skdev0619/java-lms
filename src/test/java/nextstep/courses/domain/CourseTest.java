package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @DisplayName("과정에 강의를 추가한다")
    @Test
    void addSession() {
        Course course = new Course();
        Session session = createSession();

        course.addSession(session);
        course.addSession(session);

        assertThat(course.getSessions()).hasSize(2);
    }

    private Session createSession() {
        SessionPeriod dateRange = new SessionPeriod(
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 10, 5, 23, 59)
        );
        Image image = new Image(new byte[]{}, new Size(300, 200), "jpg");
        Pricing pricing = new Pricing(PricingType.PAID, 10000);
        return new Session(dateRange, SessionStatus.RECRUITING, image, pricing, 50);
    }
}
