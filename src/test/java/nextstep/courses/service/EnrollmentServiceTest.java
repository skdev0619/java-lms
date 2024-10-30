package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class EnrollmentServiceTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentService service;

    private Session session;

    @BeforeEach
    void setUp() {
        courseRepository.save(new Course("과정", 1L));
        Course course = courseRepository.findById(1L);

        session = createSession();
        course.addSession(session);
    }

    @DisplayName("수강 신청 성공하면 수강 인원 목록에 유저가 포함된다")
    @Test
    void enrollSession() {
        EnrollRequest request = new EnrollRequest(NsUserTest.SANJIGI, session, 10000);

        service.enrollSession(request);

        assertThat(session.getStudent().size()).isEqualTo(1);
    }

    private Session createSession() {
        SessionPeriod dateRange = new SessionPeriod(
                LocalDateTime.of(2024, 10, 1, 0, 0),
                LocalDateTime.of(2024, 10, 5, 23, 59)
        );
        SessionImage image = new SessionImage("/image.png", 100, new Size(300, 200));
        Pricing pricing = new Pricing(PricingType.PAID, 10000);
        return new Session(dateRange, SessionStatus.RECRUITING, image, pricing, 50, 1L, LocalDateTime.now());
    }

}