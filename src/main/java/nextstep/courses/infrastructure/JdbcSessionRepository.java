package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionRepository;
import nextstep.courses.entity.SessionEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionEntity session) {
        String sql = "insert into session(course_id, start_date, end_date, pricing_type, session_status, available_seat, creator_id, created_at, updated_at) " +
                     "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                session.getCourse_id(),
                session.getStart_date(),
                session.getEnd_date(),
                session.getPricing_type(),
                session.getSession_status(),
                session.getAvailable_seat(),
                session.getCreator_id(),
                session.getCreated_at(),
                session.getUpdated_at());
    }

    @Override
    public SessionEntity findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, pricing_type, session_status, available_seat, creator_id, created_at, updated_at from session where id = ?";

        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> new SessionEntity(
                rs.getLong(1),
                rs.getLong(2),
                toLocalDateTime(rs.getTimestamp(3)),
                toLocalDateTime(rs.getTimestamp(4)),
                rs.getString(5),
                rs.getString(6),
                rs.getInt(7),
                rs.getLong(8),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
