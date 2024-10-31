package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionStudent;
import nextstep.courses.domain.SessionStudents;
import nextstep.courses.domain.SessionUsersRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class JdbcSessionUsersRepository implements SessionUsersRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionUsersRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionStudent student) {
        String sql = "insert into session_users (session_id, ns_user_id, creator_id, created_at, updated_at) values(?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                student.getSessionId(),
                student.getNsUserId(),
                student.getCreatorId(),
                student.getCreatedAt(),
                student.getCreatedAt());
    }

    @Override
    public SessionStudent findById(Long id) {
        String sql = "select id, session_id, ns_user_id, creator_id, created_at, updated_at from session_users where id = ?";

        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id"),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public SessionStudents findBySessionId(Long sessionId) {
        String sql = "select id, session_id, ns_user_id, creator_id, created_at, updated_at from session_users where session_id = ?";
        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id"),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")));

        List<SessionStudent> students = jdbcTemplate.query(sql, rowMapper, sessionId);
        return convertToStudents(sessionId, students);
    }

    private SessionStudents convertToStudents(Long sessionId, List<SessionStudent> students) {
        List<Long> studentIds = students.stream().map(SessionStudent::getNsUserId)
                .collect(toList());
        return new SessionStudents(sessionId, studentIds);
    }

    @Override
    public void deleteBySessionId(Long sessionId) {
        String sql = "delete from session_users where session_id = ?";
        jdbcTemplate.update(sql, sessionId);
    }

    @Override
    public int[] bulkSave(List<SessionStudent> students) {
        String sql = "insert into session_users (session_id, ns_user_id, creator_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int index) throws SQLException {
                SessionStudent student = students.get(index);
                ps.setLong(1, student.getSessionId());
                ps.setLong(2, student.getNsUserId());
                ps.setLong(3, student.getCreatorId());
                ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            }

            @Override
            public int getBatchSize() {
                return students.size();
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
