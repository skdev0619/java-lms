package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionUsersRepository;
import nextstep.courses.entity.SessionUsersEntity;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
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

    @DisplayName("강의에 등록된 유저를 저장하고 id(pk)로 조회한다")
    @Test
    void save() {
        SessionUsersEntity sessionUsers = new SessionUsersEntity(10000L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        sessionUsersRepository.save(sessionUsers);

        List<SessionUsersEntity> users = sessionUsersRepository.findBySessionId(10000L);
        assertThat(users).extracting("session_id", "ns_user_id")
                .containsExactlyInAnyOrder(new Tuple(10000L, 1L));
    }
    
    @DisplayName("특정 강의를 듣고 있는 수강생들을 조회한다")
    @Test
    void findBySessionId(){
        SessionUsersEntity user1 = new SessionUsersEntity(10000L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());
        SessionUsersEntity user2 = new SessionUsersEntity(10000L, 2L, 1L, LocalDateTime.now(), LocalDateTime.now());
        sessionUsersRepository.save(user1);
        sessionUsersRepository.save(user2);

        List<SessionUsersEntity> sessionUsers = sessionUsersRepository.findBySessionId(10000L);

        assertThat(sessionUsers).extracting("session_id", "ns_user_id")
                .contains(new Tuple(10000L, 1L), new Tuple(10000L, 2L));
    }

    @DisplayName("특정 강의를 듣고 있는 수강생 리스트를 삭제한다")
    @Test
    void deleteBySessionId(){
        sessionUsersRepository.deleteBySessionId(10000L);

        List<SessionUsersEntity> sessionUsers = sessionUsersRepository.findBySessionId(10000L);

        assertThat(sessionUsers).hasSize(0);
    }

    @DisplayName("특정 강의를 듣고 있는 수강생 목록을 일괄 추가한다")
    @Test
    void bulkInsert(){
        sessionUsersRepository.deleteBySessionId(10000L);

        List<SessionUsersEntity> usersEntities = List.of(
                new SessionUsersEntity(10000L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now()),
                new SessionUsersEntity(10000L, 2L, 2L, LocalDateTime.now(), LocalDateTime.now())
        );
        sessionUsersRepository.bulkSave(usersEntities);

        List<SessionUsersEntity> users = sessionUsersRepository.findBySessionId(10000L);
        assertThat(users).hasSize(2);
    }
}
