package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImageRepository;
import nextstep.courses.entity.SessionImageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionImageRepository sessionImageRepository;

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
    }

    @DisplayName("강의 이미지 정보를 저장하고 id(pk)로 조회한다")
    @Test
    void save() {
        SessionImageEntity sessionImage = new SessionImageEntity(10000L, "/file_path", 1L, LocalDateTime.now(), LocalDateTime.now());

        sessionImageRepository.save(sessionImage);

        SessionImageEntity sessionImageById = sessionImageRepository.findById(1L);
        assertThat(sessionImageById).extracting("session_id", "file_path")
                .containsExactlyInAnyOrder(10000L, "/file_path");
    }

}
