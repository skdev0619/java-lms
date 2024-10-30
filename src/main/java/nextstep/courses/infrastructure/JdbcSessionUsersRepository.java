package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionUsersRepository;
import nextstep.courses.entity.SessionUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    @Override
    public void deleteBySessionId(Long sessionId) {
        String sql = "delete from session_users where session_id = ?";
        jdbcTemplate.update(sql, sessionId);
    }

    @Override
    public int[] bulkSave(List<SessionUsersEntity> users) {
        String sql = "insert into session_users (session_id, ns_user_id, creator_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int index) throws SQLException {
                SessionUsersEntity entity = users.get(index);
                ps.setLong(1, entity.getSession_id());
                ps.setLong(2, entity.getNs_user_id());
                ps.setLong(3, entity.getCreator_id());
                ps.setTimestamp(4, Timestamp.valueOf(entity.getCreated_at()));
                ps.setTimestamp(5, Timestamp.valueOf(entity.getUpdated_at()));
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
