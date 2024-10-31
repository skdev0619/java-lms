package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImageRepository;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class JdbcSessionImageRepository implements SessionImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionImageEntity image) {
        String sql = "insert into session_image (session_id, file_path, file_size, width, height, creator_id, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                image.getSession_id(),
                image.getFile_path(),
                image.getFile_size(),
                image.getWidth(),
                image.getHeight(),
                image.getCreator_id(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Override
    public SessionImageEntity findById(Long id) {
        String sql = "select id, session_id, file_path, file_size, width, height, creator_id, created_at, updated_at from session_image where id = ?";

        RowMapper<SessionImageEntity> rowMapper = (rs, rowNum) -> new SessionImageEntity(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getString("file_path"),
                rs.getInt("file_size"),
                rs.getInt("width"),
                rs.getInt("height"),
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
