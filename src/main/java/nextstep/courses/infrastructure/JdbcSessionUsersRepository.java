package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionUsersRepository;
import nextstep.courses.entity.SessionUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcSessionUsersRepository implements SessionUsersRepository {

    private JdbcOperations jdbcTemplate;

    @Autowired
    public JdbcSessionUsersRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionUsersEntity users) {
        String sql = "insert into session_users (session_id, ns_user_id, creator_id, created_at, updated_at) values(?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                users.getSession_id(),
                users.getNs_user_id(),
                users.getCreator_id(),
                users.getCreated_at(),
                users.getUpdated_at());
    }

    @Override
    public SessionUsersEntity findById(Long id) {
        String sql = "select id, session_id, ns_user_id, creator_id, created_at, updated_at from session_users where id = ?";

        RowMapper<SessionUsersEntity> rowMapper = (rs, rowNum) -> new SessionUsersEntity(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getLong(4),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<SessionUsersEntity> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, ns_user_id, creator_id, created_at, updated_at from session_users where session_id = ?";
        RowMapper<SessionUsersEntity> rowMapper = (rs, rowNum) -> new SessionUsersEntity(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getLong(4),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
