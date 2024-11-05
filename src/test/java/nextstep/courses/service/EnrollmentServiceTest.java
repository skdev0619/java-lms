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
    private EnrollmentService service;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SessionImageRepository sessionImageRepository;

    @Autowired
    SessionUsersRepository sessionUsersRepository;

    private Session session;

    @BeforeEach
    void setUp() {
        Session sessionById = sessionRepository.findById(10000L).get();
        SessionImage sessionImage = sessionImageRepository.findById(10000L);

        session = new Session(
                10000L,
                sessionById.getId(),
                sessionById.getDateRange(),
                sessionById.getStatus(),
                sessionImage,
                sessionById.getPricing(),
                new SessionStudents(),
                sessionById.getAvailableSeats(),
                1L,
                LocalDateTime.now()
        );
    }

    @DisplayName("수강 신청 성공하면 수강 인원 목록에 유저가 포함된다")
    @Test
    void enrollSession() {
        EnrollRequest request = new EnrollRequest(NsUserTest.SANJIGI, session, 10000);

        service.enrollSession(request);

        SessionStudents students = sessionUsersRepository.findBySessionId(10000L);
        assertThat(students.size()).isEqualTo(1);
    }
}
