package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionStudent;
import nextstep.courses.domain.SessionStudents;
import nextstep.courses.domain.SessionUsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionUsersRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionUsersRepository sessionUsersRepository;

    @BeforeEach
    void setUp() {
        sessionUsersRepository = new JdbcSessionUsersRepository(jdbcTemplate);
        sessionUsersRepository.deleteBySessionId(10000L);
    }

    @DisplayName("특정 강의를 듣고 있는 수강생들을 조회한다")
    @Test
    void findBySessionId() {
        SessionStudent student1 = new SessionStudent(10000L, 1L);
        SessionStudent student2 = new SessionStudent(10000L, 2L);
        sessionUsersRepository.save(student1);
        sessionUsersRepository.save(student2);

        SessionStudents students = sessionUsersRepository.findBySessionId(10000L);

        assertThat(students).extracting("sessionId", "studentIds")
                .contains(10000L, List.of(1L, 2L));
    }

    @DisplayName("특정 강의를 듣고 있는 수강생 리스트를 삭제한다")
    @Test
    void deleteBySessionId() {
        SessionStudents students = sessionUsersRepository.findBySessionId(10000L);

        assertThat(students.size()).isEqualTo(0);
    }

    @DisplayName("특정 강의를 듣고 있는 수강생 목록을 일괄 추가한다")
    @Test
    void bulkInsert() {
        List<SessionStudent> students = List.of(
                new SessionStudent(10000L, 1L),
                new SessionStudent(10000L, 2L)
        );
        sessionUsersRepository.bulkSave(students);

        SessionStudents studentsBySessionId = sessionUsersRepository.findBySessionId(10000L);
        assertThat(studentsBySessionId.size()).isEqualTo(2);
    }
}
