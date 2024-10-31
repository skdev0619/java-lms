package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImageRepository;
import nextstep.courses.domain.Size;
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
        Size size = new Size(300, 200);
        SessionImage sessionImage = new SessionImage(10000L, "/file_path.jpg",100, size, 1L, LocalDateTime.now(), LocalDateTime.now());

        sessionImageRepository.save(sessionImage);

        SessionImage sessionImageById = sessionImageRepository.findById(1L);
        assertThat(sessionImageById).extracting("sessionId", "filePath")
                .containsExactlyInAnyOrder(10000L, "/file_path.jpg");
    }

}
