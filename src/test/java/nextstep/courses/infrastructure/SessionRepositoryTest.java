package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionRepository;
import nextstep.courses.entity.SessionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @DisplayName("강의 정보를 저장하고 id(pk)로 조회한다")
    @Test
    void save() {
        LocalDateTime start_date = LocalDateTime.of(2024, 10, 1, 0, 0);
        LocalDateTime end_date =LocalDateTime.of(2024, 10, 5, 0, 0);

        SessionEntity session = new SessionEntity(10000L, start_date, end_date, "P", "P", 50, 1L, LocalDateTime.now(), LocalDateTime.now());

        sessionRepository.save(session);

        SessionEntity sessionById = sessionRepository.findById(1L);
        assertThat(sessionById).extracting("course_id", "start_date", "end_date", "pricing_type", "session_status", "available_seat")
                .containsExactlyInAnyOrder(10000L, start_date, end_date, "P", "P", 50);
    }
}
