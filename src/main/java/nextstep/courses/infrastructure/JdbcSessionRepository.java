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
        String sql = "insert into session(course_id, start_date, end_date, pricing_type, price, session_status, available_seat, creator_id, created_at, updated_at) " +
                     "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                session.getCourse_id(),
                session.getStart_date(),
                session.getEnd_date(),
                session.getPricing_type(),
                session.getPrice(),
                session.getSession_status(),
                session.getAvailable_seat(),
                session.getCreator_id(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Override
    public SessionEntity findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, pricing_type, price, session_status, available_seat, creator_id, created_at, updated_at from session where id = ?";

        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> new SessionEntity(
                rs.getLong("id"),
                rs.getLong("course_id"),
                toLocalDateTime(rs.getTimestamp("start_date")),
                toLocalDateTime(rs.getTimestamp("end_date")),
                rs.getString("pricing_type"),
                rs.getInt("price"),
                rs.getString("session_status"),
                rs.getInt("available_seat"),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
